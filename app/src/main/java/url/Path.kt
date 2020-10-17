package url

enum class Path(val value: String) {
    CONTEXT_PATH("http://ja.wikipedia.org/w/api.php?"),
    CONTEXT_PATH_MOCK("http://localhost:3000/mock")
}