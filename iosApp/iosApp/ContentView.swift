import GoogleMaps
import SwiftUI
import UIKit
import Foundation
import shared

struct GoogleMapView: UIViewRepresentable {
    @Binding var userLatitude: Double
    @Binding var userLontitude: Double
    var didSelectLocation: (Double, Double) -> Void

    func makeUIView(context: Context) -> GMSMapView {
        let options = GMSMapViewOptions()
        options.camera = GMSCameraPosition(
            withLatitude: userLatitude, longtitude: userLontitude, zoom: 10.0)

        let mapView = GMSMapView(options: options)
        mapView.delegate = context.coordinator

        context.coordinator.mapView = mapView
        context.coordinator.marker.position = CLLocationCoordinate2D(
            latitude: userLatitude, longitude: userLontitude)

        return mapView
    }

    func updateUIView(_ mapView: GMSMapView, context: Context) {
        context.coordinator.marker.position = CLLocationCoordinate2D(
            latitude: userLatitude, longitude: userLontitude)
    }

    func makeCoordinator() -> MapViewCoordinator {
        return MapViewCoordinator(parent: self)
    }
}

struct ComposeView: UIViewControllerRepresentable {
    @Binding var userLatitude: Double
    @Binding var userLontitude: Double

    func makeUIViewController(context: Context) -> UIViewController {
        Main_iosKt.mainViewController(
            mapUIViewController: { () -> UIViewController in
                return UIHostingController(
                    rootView: GoogleMapView(
                        userLatitude: $userLatitude, userLontitude: $userLontitude,
                        didSelectLocation: { lat, lon in
                            self.userLatitude = lat
                            self.userLontitude = lon
                        }))
            },
            latitude: userLatitude,
            longitude: userLontitude,
            KoinAppKt.initKoinApp([], context)
        )
    }

    func updateUIViewController(_ uiViewController: UIViewController, context: Context) {}
}

struct ContentView: View {
    @State private var userLatitude: Double = 48.152958
    @State private var userLontitude: Double = 17.165769

    var body: some View {
        ComposeView(userLatitude: $userLatitude, userLontitude: $userLontitude)
            .ignoresSafeArea(.keyboard)  // Compose has own keyboard handler
    }
}
