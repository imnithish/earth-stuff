import SwiftUI
import shared

@main
struct iOSApp: App {
    
    init() {
        DIHelperKt.doInitKoin()
    }

	var body: some Scene {
		WindowGroup {
			ContentView()
		}
	}
}
