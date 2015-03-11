package com.gtja.tonywang.dragreordergridview;

import java.util.ArrayList;
import java.util.List;

import com.gtja.tonywang.dragreordergridview.lib.DragReorderGridView;
import com.gtja.tonywang.dragreordergridview.lib.DragReorderListener;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.os.Vibrator;
import android.widget.Toast;

public class MainActivity extends Activity {

	private DragReorderGridView mGridView;
	private MyAdapter mAdapter;
	private List<Item> mItems;

	private String[] sCheeseStrings = { "沪深市场", "港股行情", "天汇宝2号", "融资融券",
			"新股发行", "手机开户", "全部行情", "自选资讯", "港股通", "场内基金" };
	private int[] sCheeseIcons = { R.drawable.ht_iggt, R.drawable.ht_ihssc,
			R.drawable.ht_iqbhq, R.drawable.ht_iqqhq, R.drawable.ht_irwz,
			R.drawable.ht_irzrj, R.drawable.ht_isjkh, R.drawable.ht_ithbeh,
			R.drawable.ht_iwdzx, R.drawable.ht_ixgsg };

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_grid);

		mGridView = (DragReorderGridView) findViewById(R.id.grid);

		initData();

		mAdapter = new MyAdapter(mItems, this);
		mGridView.setAdapter(mAdapter);
		mGridView
				.setDragReorderListener(R.id.item_delete, mDragReorderListener);
	}

	private void initData() {
		mItems = new ArrayList<Item>();
		int count = sCheeseStrings.length;
		for (int i = 0; i < count; i++) {
			Item item = new Item();
			item.setLabel(sCheeseStrings[i]);
			item.setIcon(sCheeseIcons[i]);
			if (i < 3) {
				item.setRemovable(false);
			}
			mItems.add(item);
		}

		Item addBtn = new Item();
		addBtn.setLabel("");
		addBtn.setIcon(R.drawable.add_func);
		addBtn.setFixed(true);
		mItems.add(addBtn);

	}

	@Override
	public void onBackPressed() {
		if (mGridView.isDragEditMode()) {
			mGridView.quitEditMode();
			return;
		}
		super.onBackPressed();
	}

	private DragReorderListener mDragReorderListener = new DragReorderListener() {

		@Override
		public void onReorder(int fromPosition, int toPosition) {
			((MyAdapter) mGridView.getAdapter()).reorder(fromPosition,
					toPosition);
		}

		@Override
		public void onDragEnded() {
			// TODO Auto-generated method stub

		}

		@Override
		public void onItemLongClicked(int position) {
			Vibrator v = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);
			v.vibrate(50);
		}

		@Override
		public void onItemClicked(int position) {
			if (mItems.get(position).getLabel().equals("")) {
				Item item = new Item();
				item.setIcon(R.drawable.icon);
				int insertPos = mItems.size() - 1;
				item.setLabel("" + insertPos);
				mItems.add(insertPos, item);
				mAdapter.notifyDataSetChanged();
			} else {
				Toast.makeText(MainActivity.this,
						"click item " + mItems.get(position).getLabel(),
						Toast.LENGTH_SHORT).show();
			}
		}

		@Override
		public void onEditAction(int position) {
			Toast.makeText(MainActivity.this,
					"deleting " + mAdapter.getData().get(position).getLabel(),
					Toast.LENGTH_SHORT).show();
			mAdapter.removeItem(position);
			mAdapter.notifyDataSetChanged();

		}

	};

}
