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
        //Pointers to next and prev songs
       songNode *nextSong;
       songNode *prevSong;
    };
    
    songNode* head;
    songNode* end;   
public:

	 List();
	songNode* makeSong(string songURI);
    	void addSongFront(string songURI);
    	void addSongBack(string songURI);
    	void change(int pos1, int pos2);
    	bool isEmpty();
    	void displayList();
    	string deleteSongFirst();
	int getListSize();
	string getSongURI(int songNumber);
};

#endif
