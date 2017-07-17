package com.example.android.booklistingapp;

import android.app.LoaderManager;
import android.content.Loader;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class Results extends AppCompatActivity implements LoaderManager.LoaderCallbacks<List<Book>> {

    private static final String USGS_REQUEST_URL =
            "https://www.googleapis.com/books/v1/volumes?q=";
    private static final int BOOK_LOADER_ID = 1;
    private TextView mEmptyStateTextView;
    public static List<Book> mListBook;
    private ListView bookListView;

    private BookAdapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_results);
        bookListView = (ListView) findViewById(R.id.list);

        mEmptyStateTextView = (TextView) findViewById(R.id.empty_view);
        bookListView.setEmptyView(mEmptyStateTextView);

        mListBook = new ArrayList<Book>();
        mAdapter = new BookAdapter(this, 0, mListBook);

        LoaderManager loaderManager = getLoaderManager();
        loaderManager.initLoader(BOOK_LOADER_ID, null, this);

        Bundle b = getIntent().getExtras();
        String research = b.getString("ss");


        BookAsyncTask task = new BookAsyncTask();
        task.execute(USGS_REQUEST_URL + research + getString(R.string.maxResults));
    }

    private void updateUi(List<Book> book) {
        bookListView = (ListView) findViewById(R.id.list);

        BookAdapter adapter = new BookAdapter(Results.this, 0, book);

        bookListView.setAdapter(adapter);
    }

    private class BookAsyncTask extends AsyncTask<String, Void, List<Book>> {


        @Override
        protected List<Book> doInBackground(String... urls) {
            if (urls.length < 1 || urls[0] == null) {
                return null;
            }

            List<Book> result = Utils.fetchBookData(urls[0]);
            return result;
        }

        @Override
        protected void onPostExecute(List<Book> result) {
            if (result == null) {
                mEmptyStateTextView.setText(R.string.noBook);
                return;
            }
            updateUi(result);
        }
    }

    @Override
    public Loader<List<Book>> onCreateLoader(int i, Bundle bundle) {
        return new BookLoader(this, USGS_REQUEST_URL);
    }

    @Override
    public void onLoadFinished(Loader<List<Book>> loader, List<Book> book) {
        mEmptyStateTextView.setText(R.string.loading);
        mAdapter.clear();

        if (book != null && !book.isEmpty()) {
            mAdapter.addAll(book);
        }

    }

    @Override
    public void onLoaderReset(Loader<List<Book>> loader) {
        mAdapter.clear();
    }


}
