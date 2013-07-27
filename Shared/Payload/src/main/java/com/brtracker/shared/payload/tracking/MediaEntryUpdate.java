package com.brtracker.shared.payload.tracking;

import com.brtracker.shared.payload.midlink.MediaEntry;

public class MediaEntryUpdate  {

	private Long deviceHistoryId;
	private MediaEntry mediaEntry;
	
	
	public Long getDeviceHistoryId() {
		return deviceHistoryId;
	}
	public void setDeviceHistoryId(Long deviceHistoryId) {
		this.deviceHistoryId = deviceHistoryId;
	}
	public MediaEntry getMediaEntry() {
		return mediaEntry;
	}
	public void setMediaEntry(MediaEntry mediaEntry) {
		this.mediaEntry = mediaEntry;
	}
	
	
	
}
