package com.example.demo.line.message.flex.entity;

import java.util.List;

import com.example.demo.line.action.entity.Action;
import com.fasterxml.jackson.annotation.JsonInclude;
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Box {

	private String type;
	private String layout;
	private String url;
	private String uri;
	private String size;
	private List<Box> contents;
	private String backgroundColor;
	private String borderColor;
	private String borderWidth;
	private String cornerRadius;
	private String width;
	private String height;
	private Integer flex;
	private String spacing;
	private String margin;
	private String paddingAll;
	private String paddingTop;
	private String paddingBottom;
	private String paddingStart;
	private String paddingEnd;
	private String position;
	private String offsetTop;
	private String offsetBottom;
	private String offsetStart;
	private String offsetEnd;
	
	private Action action;
	
	// button
	private String style;
	private String color;
	
	// image
	private String gravity;
	private String aspectRatio;
	private String aspectMode;
	
	// text
	private String weight;
	private String text;
	private Boolean wrap;
	private Integer maxLines;
	private String decoration;
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getLayout() {
		return layout;
	}
	public void setLayout(String layout) {
		this.layout = layout;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public String getUri() {
		return uri;
	}
	public void setUri(String uri) {
		this.uri = uri;
	}
	public String getSize() {
		return size;
	}
	public void setSize(String size) {
		this.size = size;
	}
	public List<Box> getContents() {
		return contents;
	}
	public void setContents(List<Box> contents) {
		this.contents = contents;
	}
	public String getBackgroundColor() {
		return backgroundColor;
	}
	public void setBackgroundColor(String backgroundColor) {
		this.backgroundColor = backgroundColor;
	}
	public String getBorderColor() {
		return borderColor;
	}
	public void setBorderColor(String borderColor) {
		this.borderColor = borderColor;
	}
	public String getBorderWidth() {
		return borderWidth;
	}
	public void setBorderWidth(String borderWidth) {
		this.borderWidth = borderWidth;
	}
	public String getCornerRadius() {
		return cornerRadius;
	}
	public void setCornerRadius(String cornerRadius) {
		this.cornerRadius = cornerRadius;
	}
	public String getWidth() {
		return width;
	}
	public void setWidth(String width) {
		this.width = width;
	}
	public String getHeight() {
		return height;
	}
	public void setHeight(String height) {
		this.height = height;
	}
	public Integer getFlex() {
		return flex;
	}
	public void setFlex(Integer flex) {
		this.flex = flex;
	}
	public String getSpacing() {
		return spacing;
	}
	public void setSpacing(String spacing) {
		this.spacing = spacing;
	}
	public String getMargin() {
		return margin;
	}
	public void setMargin(String margin) {
		this.margin = margin;
	}
	public String getPaddingAll() {
		return paddingAll;
	}
	public void setPaddingAll(String paddingAll) {
		this.paddingAll = paddingAll;
	}
	public String getPaddingTop() {
		return paddingTop;
	}
	public void setPaddingTop(String paddingTop) {
		this.paddingTop = paddingTop;
	}
	public String getPaddingBottom() {
		return paddingBottom;
	}
	public void setPaddingBottom(String paddingBottom) {
		this.paddingBottom = paddingBottom;
	}
	public String getPaddingStart() {
		return paddingStart;
	}
	public void setPaddingStart(String paddingStart) {
		this.paddingStart = paddingStart;
	}
	public String getPaddingEnd() {
		return paddingEnd;
	}
	public void setPaddingEnd(String paddingEnd) {
		this.paddingEnd = paddingEnd;
	}
	public String getPosition() {
		return position;
	}
	public void setPosition(String position) {
		this.position = position;
	}
	public String getOffsetTop() {
		return offsetTop;
	}
	public void setOffsetTop(String offsetTop) {
		this.offsetTop = offsetTop;
	}
	public String getOffsetBottom() {
		return offsetBottom;
	}
	public void setOffsetBottom(String offsetBottom) {
		this.offsetBottom = offsetBottom;
	}
	public String getOffsetStart() {
		return offsetStart;
	}
	public void setOffsetStart(String offsetStart) {
		this.offsetStart = offsetStart;
	}
	public String getOffsetEnd() {
		return offsetEnd;
	}
	public void setOffsetEnd(String offsetEnd) {
		this.offsetEnd = offsetEnd;
	}
	public Action getAction() {
		return action;
	}
	public void setAction(Action action) {
		this.action = action;
	}
	public String getStyle() {
		return style;
	}
	public void setStyle(String style) {
		this.style = style;
	}
	public String getColor() {
		return color;
	}
	public void setColor(String color) {
		this.color = color;
	}
	public String getGravity() {
		return gravity;
	}
	public void setGravity(String gravity) {
		this.gravity = gravity;
	}
	public String getAspectRatio() {
		return aspectRatio;
	}
	public void setAspectRatio(String aspectRatio) {
		this.aspectRatio = aspectRatio;
	}
	public String getAspectMode() {
		return aspectMode;
	}
	public void setAspectMode(String aspectMode) {
		this.aspectMode = aspectMode;
	}
	public String getWeight() {
		return weight;
	}
	public void setWeight(String weight) {
		this.weight = weight;
	}
	public String getText() {
		return text;
	}
	public void setText(String text) {
		this.text = text;
	}
	public Boolean getWrap() {
		return wrap;
	}
	public void setWrap(Boolean wrap) {
		this.wrap = wrap;
	}
	public Integer getMaxLines() {
		return maxLines;
	}
	public void setMaxLines(Integer maxLines) {
		this.maxLines = maxLines;
	}
	public String getDecoration() {
		return decoration;
	}
	public void setDecoration(String decoration) {
		this.decoration = decoration;
	}
	

	
	
	
}
