import java.util.ArrayList;
import java.util.Iterator;

public class Album {

    String name;
    String artist;
    String genre;
    int songs;
    ArrayList<Song> album;

    Album(String name, String artist, String genre, int songs){
        this.name = name;
        this.artist = artist;
        this.genre = genre;
        this.songs = songs;

        album = new ArrayList<Song>();
    }

    void addSong(String songName){
        Song addedSong = new Song();
        addedSong.setName(songName);
        this.album.add(addedSong);
    }

    public ArrayList<Song> getAlbum() {
        return album;
    }

    Song getSong(String songName){
        Iterator<Song> iter = album.iterator();
        System.out.println("iterator created ----///");
        int iterator_count = 0;
        while (iter.hasNext()) {
            if (iter.next().getName().equals(songName)){
                return album.get(iterator_count);
            } else {
                iterator_count++;
            }
        }
        return null;
    }
}
