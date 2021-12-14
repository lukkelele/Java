import java.util.ArrayList;

public class Playlist {

    private ArrayList < Song > playlist;
    private String playlist_name;

    Playlist() {
        playlist = new ArrayList < Song > ();
    }

    Playlist(String name) {
        playlist = new ArrayList < Song > ();
        this.playlist_name = name;
    }

    void addSong(Song song){
        this.playlist.add(song);
    }
}