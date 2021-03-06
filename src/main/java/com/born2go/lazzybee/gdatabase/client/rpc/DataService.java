package com.born2go.lazzybee.gdatabase.client.rpc;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;

import com.born2go.lazzybee.gdatabase.shared.Blog;
import com.born2go.lazzybee.gdatabase.shared.GroupVoca;
import com.born2go.lazzybee.gdatabase.shared.Picture;
import com.born2go.lazzybee.gdatabase.shared.User;
import com.born2go.lazzybee.gdatabase.shared.Voca;
import com.born2go.lazzybee.gdatabase.shared.VocaPreview;
import com.born2go.lazzybee.gdatabase.shared.nonentity.VocaList;
import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;

@RemoteServiceRelativePath("dataservice")
public interface DataService extends RemoteService {

	public boolean verifyVoca(String voca_q);
	public Voca insertVoca(VocaPreview voca);
//	public Voca findVoca(String voca_q);
	public Voca findVoca_Web(String voca_q, boolean saveLog);
	public Voca updateVoca(Voca voca, String userId);
	public Voca verifyUpdateVoca(Voca voca, String userId);
//	public Integer getTotalVoca();
//	public Integer getTotalPreviewVoca();
	public VocaList getListVoca(String cursorStr);
	public VocaList getListPreviewVoca(String cursorStr);
	public void removeVoca(Voca voca, String userId);
	
	public User saveUser(User user);
	
	public boolean verifyBlog(String blogTitle);
	public Blog insertBlog(Blog blog, String userId);
	public Blog updateBlog(Blog blog, String userId);
	public Blog findBlogById(Long blogId);
	public Blog findBlogByTitle(String blogTitle);
	public List<Blog> getListBlog(boolean isLimited); 
	
	public Picture findPicture(Long pictureId);
	
	public String getUploadUrl(String userId);
	
	
	public LinkedHashMap<String, String> getTestVocaStep_One();
	public LinkedHashMap<String, String> getTestVocaStep_Two(LinkedHashMap<String, String> hashMap);
	public String getTestVocaStep_Three(LinkedHashMap<String, String> hashMap, String cookie,String user_id);
//	public String getTestVocaStep_Four(HashMap<String, String> hmapInput, String cookie, String user_id);
	public GroupVoca insertGroupVoca( GroupVoca g);
	public GroupVoca findGroupVoca(long id);
	public GroupVoca updateGroupVoca(GroupVoca g,String userId);
	public void removeGroup(long id,String userId);
	public List<GroupVoca> getListGroupVoca(String cursorStr);
}
