package com.brtracker.shared.payload.tracking;

import java.io.Serializable;
import java.util.List;

import com.brtracker.shared.payload.reporting.AbstractSearchResponse;
import com.brtracker.shared.payload.tracking.data.MediaComment;

public class MediaCommentResponse extends AbstractSearchResponse implements Serializable {
	
	private List<MediaComment> mediaCommentList;

	public List<MediaComment> getMediaCommentList() {
		return mediaCommentList;
	}

	public void setMediaCommentList(List<MediaComment> mediaCommentList) {
		this.mediaCommentList = mediaCommentList;
	}
	
	

}
