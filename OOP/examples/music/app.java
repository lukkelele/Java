public class app {



    public static void main(String[] args) {
        
        System.out.println("Up and running.");

        Playlist playlist = new Playlist();

        // Adding new album with songs
        Album album = new Album("Frozen God", "Yung lean", "Cloud rap", 12);
        album.addSong("Hennessy and Sailor Moon");
        album.addSong("Miami Ultras");
        album.addSong("Kirby");
        album.addSong("Hoover");

        Song toGet = album.getSong("Kirby");
        System.out.println("Song returned: "+toGet.getName());
    }
    
}
