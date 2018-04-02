package com.stitp.model;

/**联系人
 * Created by Administrator on 2016/8/29.
 */
public class Contact {

    private String id;
    private String imageName;
    private String name;
    private boolean isOnline;


    public String getImageName() {
		return imageName;
	}

	public void setImageName(String imageName) {
		this.imageName = imageName;
	}

	public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isOnline() {
        return isOnline;
    }

    public void setOnline(boolean online) {
        isOnline = online;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

	@Override
	public String toString() {
		return "Contact [id=" + id + ", imageName=" + imageName + ", name=" + name + ", isOnline=" + isOnline + "]";
	}

   
}
