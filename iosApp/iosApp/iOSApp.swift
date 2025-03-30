import SwiftUI
import composeApp
import GoogleMaps

@main
struct iOSApp: App {
    init() {
        GMSServices.provideAPIKey("AIzaSyBcDCFmejYOGSG4AXlZvBm52WwKDivH74Q")
    }
    var body: some Scene {
        WindowGroup {
            ContentView()
        }
    }
}
