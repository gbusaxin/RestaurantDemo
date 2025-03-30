import GoogleMaps
import SwiftUI
import UIKit
import Foundation
import composeApp

class MapViewCoordinator: NSObject, GMSMapViewDelegate, CLLocationManagerDelegate {
    var parent: GoogleMapView
    var locationManager = CLLocationManager()
    var marker = GMSMarker()
    
    init(parent: GoogleMapView) {
        self.parent = parent
        super.init()
        
        locationManager.delegate = self
        locationManager.requestWhenInUseAuthorization()
        locationManager.startUpdatingLocation()
    }
    
    func mapView(_ mapView: GMSMapView, didTapAt coordinate: CLLocationCoordinate2D) {
        parent.userLatitude = coordinate.latitude
        parent.userLongitude = coordinate.longitude
        parent.didSelectLocation(coordinate.latitude, coordinate.longitude)
        
        marker.position = coordinate
        marker.map = mapView
    }
    
    func locationManager(_ manager: CLLocationManager, didUpdateLocations locations: [CLLocation]){
        guard let location = locations.first else { return }
        
        parent.userLatitude = location.coordinate.latitude
        parent.userLongitude = location.coordinate.longitude
        
        marker.position = location.coordinate
    }
    
    func locationManager(_ manager: CLLocationManager, didFailWithError error: Error) {
        print("Error getting user location: \(error.localizedDescription)")
    }
}

struct GoogleMapView: UIViewRepresentable {
    @Binding var userLatitude: Double
    @Binding var userLongitude: Double
    var didSelectLocation: (Double, Double) -> Void

    func makeUIView(context: Context) -> GMSMapView {
        let options = GMSMapViewOptions()
        options.camera = GMSCameraPosition.camera(
            withLatitude: userLatitude, longitude: userLongitude, zoom: 10.0)

        let mapView = GMSMapView(options: options)
        mapView.delegate = context.coordinator

        context.coordinator.marker.map = mapView
        context.coordinator.marker.position = CLLocationCoordinate2D(
            latitude: userLatitude, longitude: userLongitude)

        return mapView
    }

    func updateUIView(_ uiView: GMSMapView, context: Context) {
        context.coordinator.marker.position = CLLocationCoordinate2D(
            latitude: userLatitude, longitude: userLongitude)
    }

    func makeCoordinator() -> MapViewCoordinator {
        return MapViewCoordinator(parent: self)
    }
}

struct ComposeView: UIViewControllerRepresentable {
    @Binding var userLatitude: Double
    @Binding var userLongitude: Double

    func makeUIViewController(context: Context) -> UIViewController {
        Main_iosKt.mainViewController(
            mapUIViewController: { () -> UIViewController in
                return UIHostingController(
                    rootView: GoogleMapView(
                        userLatitude: $userLatitude, userLongitude: $userLongitude,
                        didSelectLocation: { lat, lon in
                            self.userLatitude = lat
                            self.userLongitude = lon
                        }))
            },
            latitude: userLatitude,
            longitude: userLongitude,
            koinApplication: KoinAppKt.doInitKoinApplication(platformModules: [], context: Main_iosKt.context)
        )
    }

    func updateUIViewController(_ uiViewController: UIViewController, context: Context) {}
}

struct ContentView: View {
    @State private var userLatitude: Double = 48.152958
    @State private var userLongitude: Double = 17.165769

    var body: some View {
        ComposeView(userLatitude: $userLatitude, userLongitude: $userLongitude)
            .ignoresSafeArea(.keyboard)  // Compose has own keyboard handler
    }
}
