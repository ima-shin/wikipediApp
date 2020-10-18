package url

enum class Path(val value: String) {
    CONTEXT_PATH("http://ja.wikipedia.org/w/api.php?"),
    // 単純に127.0.0.1だとエミュレータ自身のアドレスになるらしい...
    CONTEXT_PATH_MOCK("http://10.0.2.2:3000/mock")
}