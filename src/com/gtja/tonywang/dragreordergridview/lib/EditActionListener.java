/**
 * 
 */
package com.gtja.tonywang.dragreordergridview.lib;

/**
 * @author dongxinyu.dxy
 * 
 */
public interface EditActionListener {

	/**
	 * Called when user click the action icon in edit mode,
	 * eg. delete an item
	 * 
	 * @param position
	 */
	public void onEditAction(int position);
}
