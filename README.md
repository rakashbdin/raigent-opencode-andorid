# ROC OpenCode — Android App

> Native Android shell wrapping the OpenCode web UI, with voice input/output and notifications.

## Features
- **Exact OpenCode web UI** — same look, same 35 themes
- **Voice input** — tap mic button, speak, text goes to chat
- **Voice output** — responses read aloud (TTS)
- **Multi-server** — switch between phone / laptop / AWS (built into web UI settings)
- **Notifications** — session finished, permission needed, question asked

## Build

### GitHub Actions (CI)
1. Push to `main` branch
2. APK built automatically
3. Download from Actions → Artifacts

### Local (Android Studio)
1. Open project in Android Studio
2. Connect OnePlus phone (USB debugging)
3. Run

## Architecture

```
Native Shell (Kotlin + Compose)
    └── WebView (OpenCode Web UI)
         └── JS Bridge
              ├── Voice (STT → chat input)
              ├── TTS (responses → speaker)
              └── Notifications (events → native)
```

## License
MIT
