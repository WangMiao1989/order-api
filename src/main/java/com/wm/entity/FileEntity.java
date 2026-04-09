package com.wm.entity;

import lombok.Data;

@Data
public class FileEntity {
	private Long FileId;
	private byte[] content;
	private String contentType;
}
