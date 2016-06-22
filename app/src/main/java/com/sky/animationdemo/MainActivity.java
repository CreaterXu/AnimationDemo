package com.sky.animationdemo;


import android.content.Context;
import android.content.Intent;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.animation.RotateAnimation;
import android.widget.Button;
import android.widget.TextView;

import com.google.android.gms.appindexing.Action;
import com.google.android.gms.appindexing.AppIndex;
import com.google.android.gms.common.api.GoogleApiClient;
import com.sky.animationdemo.frameAnimation.FrameAnimationActivity;
import com.sky.animationdemo.proprityAnimation.PropertyAnimationActivity;
import com.sky.animationdemo.viewAnimation.ViewAnimationActivity;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private Button okButton;
    private Button cancelButton;
    private TextView moveTxt;
    private RecyclerView choiceRecyclerView;
    public static final int VIEW_ANIMATION=0;
    public static final int DRAWABLE_ANIMATION=1;
    public static final int PROPERTY_ANIMATION=2;

    Animation animation;
    RotateAnimation rotateAnimation;
    /**
     * ATTENTION: This was auto-generated to implement the App Indexing API.
     * See https://g.co/AppIndexing/AndroidStudio for more information.
     */
    private GoogleApiClient client;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        okButton = (Button) findViewById(R.id.sports);
        okButton.setOnClickListener(this);
        cancelButton = (Button) findViewById(R.id.stop);
        cancelButton.setOnClickListener(this);

        choiceRecyclerView = (RecyclerView) findViewById(R.id.choiceRecyclerView);
        choiceRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        MAdapter mAdapter=new MAdapter(this);
        choiceRecyclerView.setAdapter(mAdapter);
        choiceRecyclerView.addItemDecoration(new DividerItemDecoration(this,LinearLayoutManager.VERTICAL));
        mAdapter.setOnItemClickListner(new MAdapter.ItemClick() {
            @Override
            public void onItemClick(View view, int position) {
                toActivity(position);
            }
        });
        moveTxt = (TextView) findViewById(R.id.hello_world);
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        client = new GoogleApiClient.Builder(this).addApi(AppIndex.API).build();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.sports) {
            //通过xml形式加载view animation
            animation = AnimationUtils.loadAnimation(this, R.anim.viewanimation);
            //通过代码形式创造动画

            RotateAnimation rotateAnimation = new RotateAnimation(0, 359);
            rotateAnimation.setDuration(5000);


            moveTxt.startAnimation(rotateAnimation);
        } else if (v.getId() == R.id.stop) {
            moveTxt.clearAnimation();
        }

    }

    /**
     * 跳转到各自的Activity
     *@param position
     * */
    private void toActivity(int position){
        Intent intent=null;
        if (position==VIEW_ANIMATION){
            intent=new Intent(this,ViewAnimationActivity.class);
        }else if (position==PROPERTY_ANIMATION){
            intent=new Intent(this, PropertyAnimationActivity.class);
        } else if (position==DRAWABLE_ANIMATION){
            intent=new Intent(this, FrameAnimationActivity.class);
        }
        startActivity(intent);
    }
    @Override
    public void onStart() {
        super.onStart();

        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        client.connect();
        Action viewAction = Action.newAction(
                Action.TYPE_VIEW, // TODO: choose an action type.
                "Main Page", // TODO: Define a title for the content shown.
                // TODO: If you have web page content that matches this app activity's content,
                // make sure this auto-generated web page URL is correct.
                // Otherwise, set the URL to null.
                Uri.parse("http://host/path"),
                // TODO: Make sure this auto-generated app URL is correct.
                Uri.parse("android-app://com.sky.animationdemo/http/host/path")
        );
        AppIndex.AppIndexApi.start(client, viewAction);
    }

    @Override
    public void onStop() {
        super.onStop();

        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        Action viewAction = Action.newAction(
                Action.TYPE_VIEW, // TODO: choose an action type.
                "Main Page", // TODO: Define a title for the content shown.
                // TODO: If you have web page content that matches this app activity's content,
                // make sure this auto-generated web page URL is correct.
                // Otherwise, set the URL to null.
                Uri.parse("http://host/path"),
                // TODO: Make sure this auto-generated app URL is correct.
                Uri.parse("android-app://com.sky.animationdemo/http/host/path")
        );
        AppIndex.AppIndexApi.end(client, viewAction);
        client.disconnect();
    }

}
/*------------------------Recyclerview 内部适配类-------------------------------------------*/
class MAdapter extends RecyclerView.Adapter<MAdapter.MViewHolder> {
    Context context;
    String[] strings=new String[]{"view animation","drawable animation","property animation"};
    MAdapter(Context context){
        this.context=context;
    }
    /*----------条目点击接口---------------*/
    public interface ItemClick{
        //单击事件
        void onItemClick(View view,int position);
    }
    ItemClick itemClick;
    //提供给RecyclerView使用
    public void setOnItemClickListner(ItemClick itemClick){
        this.itemClick=itemClick;
    }
    @Override
    public MAdapter.MViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        MViewHolder mViewHolder=new MViewHolder(LayoutInflater.from(context).inflate(R.layout.item_animation, parent, false));
        return mViewHolder;
    }

    @Override
    public void onBindViewHolder(MAdapter.MViewHolder holder, final int position) {
        holder.aniTextVeiw.setText(strings[position]);
        holder.aniTextVeiw.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                itemClick.onItemClick(v,position);
            }
        });
    }

    @Override
    public int getItemCount() {
        return 3;
    }

    class MViewHolder extends RecyclerView.ViewHolder {
        TextView aniTextVeiw;
        public MViewHolder(View itemView) {
            super(itemView);
            aniTextVeiw=(TextView)itemView.findViewById(R.id.xv);
        }
    }
}
/*********************RecyclerView的Item分割线类********************************************/
/**
 * This class is from the v7 samples of the Android SDK. It's not by me!
 * <p/>
 * See the license above for details.
 */
 class DividerItemDecoration extends RecyclerView.ItemDecoration {

    private static final int[] ATTRS = new int[]{
            android.R.attr.listDivider
    };

    public static final int HORIZONTAL_LIST = LinearLayoutManager.HORIZONTAL;

    public static final int VERTICAL_LIST = LinearLayoutManager.VERTICAL;

    private Drawable mDivider;

    private int mOrientation;

    public DividerItemDecoration(Context context, int orientation) {
        final TypedArray a = context.obtainStyledAttributes(ATTRS);
        mDivider = a.getDrawable(0);
        a.recycle();
        setOrientation(orientation);
    }

    public void setOrientation(int orientation) {
        if (orientation != HORIZONTAL_LIST && orientation != VERTICAL_LIST) {
            throw new IllegalArgumentException("invalid orientation");
        }
        mOrientation = orientation;
    }

    @Override
    public void onDraw(Canvas c, RecyclerView parent) {
        Log.v("recyclerview-itemde", "onDraw()");

        if (mOrientation == VERTICAL_LIST) {
            drawVertical(c, parent);
        } else {
            drawHorizontal(c, parent);
        }

    }


    public void drawVertical(Canvas c, RecyclerView parent) {
        final int left = parent.getPaddingLeft();
        final int right = parent.getWidth() - parent.getPaddingRight();

        final int childCount = parent.getChildCount();
        for (int i = 0; i < childCount; i++) {
            final View child = parent.getChildAt(i);
            android.support.v7.widget.RecyclerView v = new android.support.v7.widget.RecyclerView(parent.getContext());
            final RecyclerView.LayoutParams params = (RecyclerView.LayoutParams) child
                    .getLayoutParams();
            final int top = child.getBottom() + params.bottomMargin;
            final int bottom = top + mDivider.getIntrinsicHeight();
            mDivider.setBounds(left, top, right, bottom);
            mDivider.draw(c);
        }
    }

    public void drawHorizontal(Canvas c, RecyclerView parent) {
        final int top = parent.getPaddingTop();
        final int bottom = parent.getHeight() - parent.getPaddingBottom();

        final int childCount = parent.getChildCount();
        for (int i = 0; i < childCount; i++) {
            final View child = parent.getChildAt(i);
            final RecyclerView.LayoutParams params = (RecyclerView.LayoutParams) child
                    .getLayoutParams();
            final int left = child.getRight() + params.rightMargin;
            final int right = left + mDivider.getIntrinsicHeight();
            mDivider.setBounds(left, top, right, bottom);
            mDivider.draw(c);
        }
    }

    @Override
    public void getItemOffsets(Rect outRect, int itemPosition, RecyclerView parent) {
        if (mOrientation == VERTICAL_LIST) {
            outRect.set(0, 0, 0, mDivider.getIntrinsicHeight());
        } else {
            outRect.set(0, 0, mDivider.getIntrinsicWidth(), 0);
        }
    }
}
