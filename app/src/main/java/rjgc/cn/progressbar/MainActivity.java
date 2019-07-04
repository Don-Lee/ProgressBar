package rjgc.cn.progressbar;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private ProgressBar mProgressBar;
    private Button mBtnAdd;
    private Button mBtnReduce;
    private Button mBtnReset;
    private TextView mTv;
    private Button mBtnShow;
    int max;

    private ProgressDialog mProgressDialog;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);
        assignViews();
    }

    private void assignViews() {
        mProgressBar = (ProgressBar) findViewById(R.id.progressBar);
        mBtnAdd = (Button) findViewById(R.id.btn_add);
        mBtnReduce = (Button) findViewById(R.id.btn_reduce);
        mBtnReset = (Button) findViewById(R.id.btn_reset);
        mTv = (TextView) findViewById(R.id.tv);
        mBtnShow= (Button) findViewById(R.id.btn_dialog);

        mBtnAdd.setOnClickListener(this);
        mBtnReduce.setOnClickListener(this);
        mBtnReset.setOnClickListener(this);
        mBtnShow.setOnClickListener(this);

        //第一进度条的进度
        int first=mProgressBar.getProgress();
        //第二进度条的进度
        int second=mProgressBar.getSecondaryProgress();
        //进度条的最大进度
        max=mProgressBar.getMax();

        mTv.setText("第一进度百分比:"+(int)(first/(float)max*100)+"%,第二进度百分比:"+(int)(second/(float)max*100)+"%");
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
        switch (v.getId()){
            case R.id.btn_add:
                //增加第一二进度10个刻度
                mProgressBar.incrementProgressBy(10);
                mProgressBar.incrementSecondaryProgressBy(10);
                break;
            case R.id.btn_reduce:
                //减少第一二进度10个刻度
                mProgressBar.incrementProgressBy(-10);
                mProgressBar.incrementSecondaryProgressBy(-10);
                break;
            case R.id.btn_reset:
                mProgressBar.setProgress(50);
                mProgressBar.setSecondaryProgress(80);
                break;
            case R.id.btn_dialog:
                mProgressDialog=new ProgressDialog(MainActivity.this);
                //设置显示风格
                mProgressDialog.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
                mProgressDialog.setTitle("Dialog下载");
                mProgressDialog.setMessage("下载测试");
                mProgressDialog.setIcon(R.mipmap.ic_launcher);

                //设置关于ProgressBar的属性
                mProgressDialog.setMax(100);//最大进度
                mProgressDialog.incrementProgressBy(50);//已经下载的进度
                mProgressDialog.setIndeterminate(false);//精确显示进度
                mProgressDialog.setButton(DialogInterface.BUTTON_POSITIVE,"确定",new DialogInterface.OnClickListener(){
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Toast.makeText(MainActivity.this, "下载", Toast.LENGTH_SHORT).show();
                    }
                });

                //是否通过返回按钮退出对话框
                mProgressDialog.setCancelable(true);
                //显示ProgressDialog
                mProgressDialog.show();
                break;

            default:
                break;
        }
        mTv.setText("第一进度百分比:"+(int)(mProgressBar.getProgress()/(float)max*100)+"%,第二进度百分比:"+(int)(mProgressBar.getSecondaryProgress()/(float)max*100)+"%");
    }
}
