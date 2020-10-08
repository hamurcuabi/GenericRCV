package com.emrhmrc.genericrecycler.multisnap

/**
 * Listener to notify being snapped.
 */
interface OnSnapListener {

    /**
     * Called when RecyclerView items are snapped.
     *
     * @param position snapped position
     */
    fun snapped(position: Int)
}
