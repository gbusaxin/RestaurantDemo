import SwiftUI
import shared
import GoogleMaps

@main
struct iOSApp: App {
    init() {
        GMSService.provideAPIKey("AIzaSyBcDCFmejYOGSG4AXlZvBm52WwKDivH74Q")
    }
    var body: some Scene {
        WindowGroup {
            ContentView()
        }
    }
}
