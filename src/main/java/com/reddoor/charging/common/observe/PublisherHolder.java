package com.reddoor.charging.common.observe;

public class PublisherHolder {
	public static Publisher publisher;
	
	public static Publisher getPublisher() {
		return publisher;
	}

	public static void setPublisher(Publisher publisher) {
		PublisherHolder.publisher = publisher;
	}
}
