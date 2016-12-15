#ifndef LIST
#define LIST

#include <string>
using namespace std;

class List {
private:

    //Define node for each song in list
    struct songNode{
        //Data for each node
	string songURI;
	string songName;
	string songArtist;
	string songAlbum;
        //Pointer to next song
       	songNode *nextSong;
      };
    	songNode* head;
    	songNode* end; 

public:

	List();
	songNode* makeSong(string songURI,string songName,string songArtist,string songAlbum);
    	void addSongBack(string songURI,string songName,string songArtist,string songAlbum);
    	bool isEmpty();
    	string deleteSongFirst();
	int getListSize();
	string getSongURI(int songNumber);
	string getSongName(int songNumber);
	string getSongArtist(int songNumber);
	string getSongAlbum(int songNumber);
	int size;
};

#endif
