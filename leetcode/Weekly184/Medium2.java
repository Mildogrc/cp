class Medium2 {
    public String entityParser(String text) {
        text = text.replaceAll("&quot;", "\\\"").replaceAll("&apos;", "\\'").replaceAll("&amp;", "&")
				.replaceAll("&gt;", ">").replaceAll("&lt;", "<").replaceAll("&frasl;", "/");
        return text;
    }
}
