/**
 * 
 */
package com.gtja.tonywang.dragreordergridview.lib;

/**
 * drag interface , include three callback method:
 * <p>
 * start drag
 * </p>
 * {@link DragReorderListener#onItemLongClicked()} s *
 * <p>
 * move reorder
 * </p>
 * {@link DragReorderListener#onReorder(int, int)}
 * <p>
 * end drag
 * </p>
 * {@link DragReorderListener#onDragEnded()}
 * 
 * @author dongxinyu.dxy
 * 
 */
public interface DragReorderListener {

	/**
	 * Called when user drags one item over another, and animations will
	 * indicate this reorder. Client should update data order in this method
	 * 
	 * @param fromPosition
	 * @param toPosition
	 */
	public void onReorder(int fromPosition, int toPosition);

	/**
	 * Notify when user long click the item and start to drag. Client should
	 * probably make a vibration here to indicate long click
	 */
	public void onItemLongClicked();

	/**
	 * Notify when user drops the item. Client should probably persistent the
	 * reordered data.
	 */
	public void onDragEnded();
}
