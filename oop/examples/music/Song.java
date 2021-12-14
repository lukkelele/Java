public class Song {

    private Artist artist;
    private String name;
    private String genre;
    private String format;

    Song() {

    }

    Song(Artist artist_name, String name, String genre, String format) {
        this.artist = artist_name;
        this.name = name;
        this.genre = genre;
        this.format = format;
    }

    public String getName() {
        return name;
    }

    public Artist getArtist() {
        return artist;
    }

    public String getGenre() {
        return genre;
    }

    public void setName(String name) {
        this.name = name;
    }
}