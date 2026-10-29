package xyz.eliothmonroy.a3dtest

/** Offline editorial catalog. Coordinates open the installed map application. */
internal data class Attraction(
    val id: String,
    val name: String,
    val area: String,
    val category: String,
    val description: String,
    val detail: String,
    val model: String,
    val lat: Double,
    val lon: Double,
) {
    companion object {
        val ALL: List<Attraction> = listOf(
        Attraction("bellas", "Palacio de Bellas Artes", "Centro Histórico", "Architecture",
            "Where art meets an icon.",
            "A marble masterpiece with a luminous copper dome. Discover the palace’s Art Nouveau exterior, Art Deco interiors, and celebrated Mexican murals.\n\nLook closer\nThe dome’s warm amber tones make this one of the city’s most recognizable silhouettes.",
            "palace",19.4352,-99.1412),
        Attraction("angel", "Ángel de la Independencia", "Paseo de la Reforma", "Monuments",
            "The city’s golden landmark.",
            "A winged Victory rises above Paseo de la Reforma. This monument commemorates Mexico’s independence and anchors one of the city’s grandest avenues.\n\nLook closer\nThe sculptural column is framed by a circular plaza and the architecture of Reforma.",
            "angel",19.4270,-99.1677),
        Attraction("castle", "Castillo de Chapultepec", "Bosque de Chapultepec", "Museums",
            "History above the treetops.",
            "Set on Chapultepec Hill, this historic castle is home to the National Museum of History. Its terraces look across the park toward Paseo de la Reforma.\n\nLook closer\nExplore the contrast between formal gardens, stone terraces, and the castle’s distinctive tower.",
            "castle",19.4205,-99.1817),
        Attraction("soumaya", "Museo Soumaya", "Nuevo Polanco", "Museums",
            "An extraordinary curve.",
            "A shimmering, sculptural museum in Plaza Carso. Its curved exterior is covered in hexagonal aluminum tiles and houses a broad collection of art.\n\nLook closer\nMove around the model to see how the twisting silhouette changes from every angle.",
            "soumaya",19.4407,-99.2047),
        Attraction("cathedral", "Catedral Metropolitana", "Zócalo", "Architecture",
            "Centuries carved in stone.",
            "Facing the Zócalo, the Metropolitan Cathedral brings together centuries of architectural history. Its twin bell towers form a defining part of the historic center.\n\nLook closer\nNotice the monumental façade, central dome, and symmetrical towers.",
            "cathedral",19.4346,-99.1332),
        Attraction("revolution", "Monumento a la Revolución", "Plaza de la República", "Monuments",
            "A monumental perspective.",
            "A vast copper dome crowns this landmark dedicated to the Mexican Revolution. Its four stone piers frame the open space of Plaza de la República.\n\nLook closer\nThe model reveals the huge archways beneath the dome.",
            "revolution",19.4362,-99.1547)
        )
    }
}
