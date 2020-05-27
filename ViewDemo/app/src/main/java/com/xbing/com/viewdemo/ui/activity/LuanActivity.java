package com.xbing.com.viewdemo.ui.activity;

import android.app.Activity;
import android.app.LoaderManager;
import android.content.CursorLoader;
import android.content.Loader;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewConfiguration;
import android.view.ViewGroup;

import com.xbing.com.viewdemo.R;
import com.xbing.com.viewdemo.service.utils.Cn2Spell;

/**
 * Created by zhaobing on 2016/7/1.
 */
public class LuanActivity extends Activity{
    public int mLastX,mLastY;
    public int translationx = 0;
    public View mView;
    private View mView2;

    private LoaderManager mLoaderManager;

    private LoadCallbak mLoader = new LoadCallbak();

    public class LoadCallbak implements LoaderManager.LoaderCallbacks{

        @Override
        public Loader onCreateLoader(int i, Bundle bundle) {
            Log.d("liftcycle",getClass().getSimpleName()+":onCreateLoader()");
            CursorLoader loader = new CursorLoader(LuanActivity.this, Uri.parse("content://com.app"),null,null,null,null);
            return loader;
        }

        @Override
        public void onLoadFinished(Loader loader, Object o) {
            Log.d("liftcycle",getClass().getSimpleName()+":onLoadFinished()");

        }

        @Override
        public void onLoaderReset(Loader loader) {
            Log.d("liftcycle",getClass().getSimpleName()+":onLoaderReset()");

        }
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.d("liftcycle",getClass().getSimpleName()+":onstop()");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.d("liftcycle",getClass().getSimpleName()+":ondestroy()");
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.d("liftcycle",getClass().getSimpleName()+":onpause()");
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.d("liftcycle",getClass().getSimpleName()+":onresume()");
    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.d("liftcycle",getClass().getSimpleName()+":onstart()");
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.luan_activity);

        mLoaderManager = this.getLoaderManager();
        mLoaderManager.initLoader(100,null,mLoader);

        Log.d("liftcycle",getClass().getSimpleName()+":onCreate()");
        final int touchslop = ViewConfiguration.get(LuanActivity.this).getScaledTouchSlop();
        final int doubleslop = ViewConfiguration.get(LuanActivity.this).getScaledDoubleTapSlop();
        mView = findViewById(R.id.v_event);
        mView2 =  findViewById(R.id.v_event2);
        mView.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                int x = (int) motionEvent.getRawX();
                int y = (int) motionEvent.getRawY();
                switch(motionEvent.getAction()){
                    case MotionEvent.ACTION_DOWN:
                        break;
                    case MotionEvent.ACTION_MOVE:
                        int deltaX = x-mLastX;//delta带表手指触摸点的偏移量   而translation代表view x相对于位置left的便宜量
                        int deltaY = y-mLastY;
                        float translationX = view.getTranslationX()+deltaX;
                        float translationy = view.getTranslationY()+deltaY;
                        view.setTranslationX(translationX);
                        view.setTranslationY(translationy);
                        break;
                    case MotionEvent.ACTION_UP:
                        break;
                }
                mLastY = y;
                mLastX = x;
                return true;
            }
        });
        kong();
        findViewById(R.id.btn_move).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                switch(view.getId()){
                    case R.id.btn_move:

                        translationx += 20;
                        view.setTranslationX(translationx);
//                        view.setLeft(264);
                        Log.e("view","vleft:" + view.getLeft() +",right:" + view.getRight()
                                + ",x:" + view.getX()+ ",translationx:" +view.getTranslationX()
                                + ",width" + view.getWidth() + ",touchslop:" + touchslop + ",doubleslop:"
                                + doubleslop);
                        Log.e("view","mleft:" + mView.getLeft() +",right:" + mView.getRight()
                                + ",x:" + mView.getX()+ ",translationx:" +mView.getTranslationX()
                                + ",width" + mView.getWidth() + ",touchslop:" + touchslop + ",doubleslop:"
                                + doubleslop);
                        Log.e("view","mleft:" + mView2.getLeft() +",right:" + mView2.getRight()
                                + ",x:" + mView2.getX()+ ",translationx:" +mView2.getTranslationX()
                                + ",width" + mView2.getWidth() + ",touchslop:" + touchslop + ",doubleslop:"
                                + doubleslop);

                        /**
                         * 针对mView2改动，测试marginleft值改变的属性
                         */
                    {
                        ViewGroup.MarginLayoutParams params = (ViewGroup.MarginLayoutParams)mView2.getLayoutParams();
                        params.width+=100;
                        params.height+=100;
                        mView2.requestLayout();

                        Log.e("view","mleft:" + mView2.getLeft() +",right:" + mView2.getRight()
                                + ",x:" + mView2.getX()+ ",translationx:" +mView2.getTranslationX()
                                + ",width" + mView2.getWidth() + ",touchslop:" + touchslop + ",doubleslop:"
                                + doubleslop);
                    }
                    break;
                }
            }
        });


    }

    public void kong(){
        Log.i("testSpell", "testSpell--->" + Cn2Spell.getInstance().getSelling("中国"));
        Log.i("testSpell", "testSpell--->" + Cn2Spell.getInstance().getSelling("日本"));
        Log.i("testSpell", "testSpell--->" + Cn2Spell.getInstance().getSelling("张三"));
        Log.i("testSpell", "testSpell--->" + Cn2Spell.getInstance().getSelling("李四"));
        Log.i("testSpell", "testSpell--->" + Cn2Spell.getInstance().getSelling("赵兵"));
        Log.i("testSpell", "testSpell--->" + Cn2Spell.getInstance().getSelling("王志强"));
        Log.i("testSpell", "testSpell--->" + Cn2Spell.getInstance().getSelling("白惠凯"));

//        getSkypeContactList();
    }

    private void getSkypeContactList() {
        Cursor c = getContentResolver().query
                (ContactsContract.RawContacts.CONTENT_URI,
                        new String[] { ContactsContract.RawContacts.CONTACT_ID,ContactsContract.RawContacts.DISPLAY_NAME_PRIMARY,ContactsContract.RawContacts.DISPLAY_NAME_ALTERNATIVE },
                        ContactsContract.RawContacts.ACCOUNT_TYPE + "= ?",
                        new String[] { "com.skype.contacts.sync" }, null);

        int contactNameColumn = c
                .getColumnIndex(ContactsContract.Contacts.DISPLAY_NAME_ALTERNATIVE);
        int contactIdColumn = c.getColumnIndex(ContactsContract.RawContacts.CONTACT_ID);
        int contactNumberColumn = c.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER);
        int contactUriColumn = c.getColumnIndex(ContactsContract.Contacts.PHOTO_THUMBNAIL_URI);


        int count = c.getCount();

        for (int i = 0; i < count; i++) {
            c.moveToPosition(i);
            String name = c.getString(contactNameColumn);
//            String id = c.getString(contactIdColumn);
            String id = " id";
            String uri = " uri";//c.getString(contactUriColumn);
            String phoneNumber = " number";//c.getString(contactNumberColumn);
            Log.i("KEY", "key:\nid:" + id + ",uri:" + uri +  ",name:" + name + ",phone:" + phoneNumber );
        }
    }
}
