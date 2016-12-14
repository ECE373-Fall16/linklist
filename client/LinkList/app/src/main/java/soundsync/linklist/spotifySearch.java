package soundsync.linklist;

import android.content.Context;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.SearchView;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import kaaes.spotify.webapi.android.SpotifyApi;
import kaaes.spotify.webapi.android.SpotifyCallback;
import kaaes.spotify.webapi.android.SpotifyError;
import kaaes.spotify.webapi.android.SpotifyService;
import kaaes.spotify.webapi.android.models.Track;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.View;
import android.widget.Toast;
import java.util.List;
import kaaes.spotify.webapi.android.models.Track;
import kaaes.spotify.webapi.android.models.TracksPager;
import retrofit.client.Response;

/**
     * Created by Monkeysee on 12/8/2016.
     */
public class spotifySearch extends AppCompatActivity {

        Button goBack;

        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_spotify_search);

            goBack = (Button)findViewById(R.id.goBack);

            goBack.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v){
                    Intent intent = new Intent(spotifySearch.this, PlayPage.class);   //create intent to change to PlayPage (to be written)
                    startActivity(intent);
                }
            });

        }
        //-----------------------SPOTIFY API---------------------------

        public String Search(){
            EditText search = (EditText) findViewById(R.id.searchView);
            return search.getText().toString();

        }


        public void oceanManSearch(View view) {
            SpotifyApi api = new SpotifyApi();

            SpotifyService spotify = api.getService();

            spotify.searchTracks(Search(), new SpotifyCallback<TracksPager>() {
                @Override
                public void success(TracksPager tracksPager, Response response){
                    int size = tracksPager.tracks.items.size();

                    Log.d("Success Response", response.toString());
                    ArrayList<String> list = new ArrayList<String>();

                    SearchAdapter adapter = new SearchAdapter(tracksPager.tracks.items, spotifySearch.this);


                    ListView listView = (ListView) findViewById(R.id.searchListView);
                    listView.setAdapter(adapter);


                }

                @Override
                public void failure(SpotifyError error){
                    Log.d("Search Failure", error.toString());
                }
            });

        }


        Context mContext;

        Search.View mView;
        private String mCurrentQuery;

        private static final String TAG = SearchPresenter.class.getSimpleName();
        public static final int PAGE_SIZE = 20;



        private SearchPager mSearchPager;
        private SearchPager.CompleteListener mSearchListener;

        private Player mPlayer;

        public void loadMoreResults() {
            Log.d(TAG, "Load more...");
            mSearchPager.getNextPage(mSearchListener);    }

        public void selectTrack(Track item) {
            String previewUrl = item.preview_url;

            if (previewUrl == null) {
                logMessage("Track doesn't have a preview");
                return;
            }

            if (mPlayer == null) return;

            String currentTrackUrl = mPlayer.getCurrentTrack();

            if (currentTrackUrl == null || !currentTrackUrl.equals(previewUrl)) {
                mPlayer.play(previewUrl);
            } else if (mPlayer.isPlaying()) {
                mPlayer.pause();
            } else {
                mPlayer.resume();
            }
        }

        private void logError(String msg) {
            Toast.makeText(mContext, "Error: " + msg, Toast.LENGTH_SHORT).show();
            Log.e(TAG, msg);
        }

        private void logMessage(String msg) {
            Toast.makeText(mContext, msg, Toast.LENGTH_SHORT).show();
            Log.d(TAG, msg);
        }
        public void search(@Nullable String searchQuery) {
            if (searchQuery != null && !searchQuery.isEmpty() && !searchQuery.equals(mCurrentQuery)) {
                logMessage("query text submit " + searchQuery);
                mCurrentQuery = searchQuery;
                mView.reset();
                mSearchListener = new SearchPager.CompleteListener() {
                    @Override
                    public void onComplete(List<Track> items) {
                        mView.addData(items);
                    }

                    @Override
                    public void onError(Throwable error) {
                        logError(error.getMessage());
                    }
                };
                mSearchPager.getFirstPage(searchQuery, PAGE_SIZE, mSearchListener);
            }
        }

    }


