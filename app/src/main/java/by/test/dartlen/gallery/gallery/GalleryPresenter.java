package by.test.dartlen.gallery.gallery;

import android.support.annotation.NonNull;
import android.util.Log;

import java.util.Date;

import by.test.dartlen.gallery.data.GalleryDataSource;
import by.test.dartlen.gallery.data.GalleryRepository;
import by.test.dartlen.gallery.data.remote.retrofit.RetrofitResponseCallback;
import by.test.dartlen.gallery.data.remote.retrofit.image.ImageData;
import by.test.dartlen.gallery.data.remote.retrofit.image.ResponseDataImage;
import by.test.dartlen.gallery.data.remote.retrofit.image.ResponseDataImagePost;
import by.test.dartlen.gallery.login.LoginContract;

import static com.google.common.base.Preconditions.checkNotNull;

/***
 * Created by Dartlen on 28.10.2017.
 */

public class GalleryPresenter implements GalleryContract.Presenter {

    private GalleryDataSource mGalleryRepository;
    private GalleryContract.View mGalleryView;

    public GalleryPresenter(@NonNull GalleryRepository galleryRepository, @NonNull GalleryContract.View galleryView){
        mGalleryRepository = checkNotNull(galleryRepository, "gallery cannot be null");
        mGalleryView = checkNotNull(galleryView, "galleryView cannot be null!");

        mGalleryView.setPresenter(this);
        Log.d("okk","Gallery init");
        loadImages();
        //postImage();
        //loadImages();
    }

    @Override
    public void start() {

        //mGalleryView.showLogin();
    }

    @Override
    public void loadImages() {

        String token = "TTlobxhNFvm2zO2Jwm3uniSJKOaTzHltywQAvqNZ73hTVfqmmwPCFFS8UDJ7IUx3";
        int page = 1;
        //String token = mGalleryRepository.getUser().getToken();
        mGalleryRepository.getImages(new GalleryDataSource.LoadImageCallback() {
            @Override
            public void onDataLoaded(ResponseDataImage dataResponse) {
                mGalleryView.showImages(dataResponse.getData());
            }

            @Override
            public void onError(String error) {
                Log.d("okk","loading images error");
            }
        },page,token);
    }


    @Override
    public void postImage() {
        String token = "TTlobxhNFvm2zO2Jwm3uniSJKOaTzHltywQAvqNZ73hTVfqmmwPCFFS8UDJ7IUx3";
        String image = "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";
        ImageData data = new ImageData(image, 1262307723, 3, 3);

        mGalleryRepository.postImage(new GalleryDataSource.ImagePostCallback() {
            @Override
            public void onDataLoaded(ResponseDataImagePost dataResponse) {

            }

            @Override
            public void onError(String error) {

            }
        }, token, data);

    }


}
