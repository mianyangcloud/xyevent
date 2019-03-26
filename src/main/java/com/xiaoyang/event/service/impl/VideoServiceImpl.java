package com.xiaoyang.event.service.impl;

import java.util.Collections;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.xiaoyang.event.common.PageModel;
import com.xiaoyang.event.constant.VideoStatus;
import com.xiaoyang.event.dao.VideoMapper;
import com.xiaoyang.event.domain.Group;
import com.xiaoyang.event.domain.Video;
import com.xiaoyang.event.dto.PageDto;
import com.xiaoyang.event.dto.VideoDto;
import com.xiaoyang.event.exception.SysException;
import com.xiaoyang.event.service.GroupService;
import com.xiaoyang.event.service.VideoService;
import com.xiaoyang.event.utils.DateUtil;
import com.xiaoyang.event.utils.VodUtil;

@Service
public class VideoServiceImpl implements VideoService {

	@Autowired
	VideoMapper videoMapper;
	
	@Autowired
	GroupService groupService;
	
	@Override
	public PageDto list(int eventId, String searchText, PageModel pageModel) {
        Page<Video> page = PageHelper.startPage(pageModel.getPageNumber(), pageModel.getPageSize(), true);
        if(StringUtils.isEmpty(searchText)) {
			searchText = "";
		}else{
			searchText = "%" + searchText + "%";
		}
		List<Video> list = videoMapper.list(eventId, searchText);
		PageDto pageDto = new PageDto();
		if(CollectionUtils.isEmpty(list)){
			pageDto.setRows(Collections.EMPTY_LIST);
        }else {
        	List<Group> groupList = groupService.list(1, list.get(0).getEventId(), null);
        	Map groupMap = Maps.newHashMap();
        	for(Group group : groupList) {
        		groupMap.put(group.getId(), group.getTitle());
        	}
        	List<VideoDto> dtoList = Lists.newArrayList();
        	for(Video video : list) {
        		VideoDto videoDto = VideoDto.of();
        		BeanUtils.copyProperties(video, videoDto);
        		videoDto.setCreateTime(DateUtil.stampToDate(video.getCtime().getTime()));
        		if(groupMap.get(videoDto.getGroupId()) != null) {
        			videoDto.setGroupTitle(groupMap.get(videoDto.getGroupId()).toString());
        		}
        		dtoList.add(videoDto);
        	}
        	pageDto.setRows(dtoList);
        }
		pageDto.setTotal((int)page.getTotal());
		return pageDto;
	}
	
	@Override
	public PageDto listByGroupId(int groupId, String searchText, PageModel pageModel) {
        Page<Video> page = PageHelper.startPage(pageModel.getPageNumber(), pageModel.getPageSize(), true);
        if(StringUtils.isEmpty(searchText)) {
			searchText = "";
		}else{
			searchText = "%" + searchText + "%";
		}
		List<Video> list = videoMapper.listByGroupId(groupId, searchText);
		PageDto pageDto = new PageDto();
		if(CollectionUtils.isEmpty(list)){
			pageDto.setRows(Collections.EMPTY_LIST);
        }else {
        	List<Group> groupList = groupService.list(1, list.get(0).getEventId(), null);
        	Map groupMap = Maps.newHashMap();
        	for(Group group : groupList) {
        		groupMap.put(group.getId(), group.getTitle());
        	}
        	List<VideoDto> dtoList = Lists.newArrayList();
        	for(Video video : list) {
        		VideoDto videoDto = VideoDto.of();
        		BeanUtils.copyProperties(video, videoDto);
        		videoDto.setCreateTime(DateUtil.stampToDate(video.getCtime().getTime()));
        		if(groupMap.get(videoDto.getGroupId()) != null) {
        			videoDto.setGroupTitle(groupMap.get(videoDto.getGroupId()).toString());
        		}
        		dtoList.add(videoDto);
        	}
        	pageDto.setRows(dtoList);
        }
		pageDto.setTotal((int)page.getTotal());
		return pageDto;
	}
	
	@Override
	public PageDto listByUserId(int userId, String searchText, PageModel pageModel) {
        Page<Video> page = PageHelper.startPage(pageModel.getPageNumber(), pageModel.getPageSize(), true);
        if(StringUtils.isEmpty(searchText)) {
			searchText = "";
		}else{
			searchText = "%" + searchText + "%";
		}
		List<Video> list = videoMapper.listByUserId(userId, searchText);
		PageDto pageDto = new PageDto();
		if(CollectionUtils.isEmpty(list)){
			pageDto.setRows(Collections.EMPTY_LIST);
        }else {
        	List<Group> groupList = groupService.list(1, list.get(0).getEventId(), null);
        	Map groupMap = Maps.newHashMap();
        	for(Group group : groupList) {
        		groupMap.put(group.getId(), group.getTitle());
        	}
        	List<VideoDto> dtoList = Lists.newArrayList();
        	for(Video video : list) {
        		VideoDto videoDto = VideoDto.of();
        		BeanUtils.copyProperties(video, videoDto);
        		videoDto.setCreateTime(DateUtil.stampToDate(video.getCtime().getTime()));
        		if(groupMap.get(videoDto.getGroupId()) != null) {
        			videoDto.setGroupTitle(groupMap.get(videoDto.getGroupId()).toString());
        		}
        		dtoList.add(videoDto);
        	}
        	pageDto.setRows(dtoList);
        }
		pageDto.setTotal((int)page.getTotal());
		return pageDto;
	}
	
	@Override
	public void update(int groupId, String videoId) {
		videoId = videoId.trim().replace(" ", "");
		updateByArrayId(groupId, videoId.split(","));
	}
	
	@Override
	public void delete(String id) {
		id = id.trim().replace(" ", "");
		deleteByArrayId(id.split(","));
	}

	@Override
	public void add(Video video) {
		int ret = videoMapper.add(video);
		if(ret != 1) {
			throw new SysException();
		}
	}

	@Override
	public void update(Video video) {
		int ret = videoMapper.update(video);
		if(ret != 1) {
			throw new SysException();
		}
	}

	@Override
	public Video findById(int id) {
		return videoMapper.findById(id);
	}
	
	@Override
	public Video findByVideoId(int id, String videoId) {
		Video video = videoMapper.findById(id);
		return updateVideoFullInfo(video, videoId);
	}
	
	@Override
	public Video findByVideoId(String videoId) {
		Video video = videoMapper.findByVideoId(videoId);
		return updateVideoFullInfo(video, videoId);
	}
	
	@Override
	public Video updateVideoInfo(Video video) {
		Video oldVideo = findById(video.getId());
		if(!StringUtils.isEmpty(video.getTitle())) {
			oldVideo.setTitle(video.getTitle());
		}
		if(!StringUtils.isEmpty(video.getHeadImage())) {
			oldVideo.setHeadImage(video.getHeadImage());
		}
		if(video.getGroupId()!=null) {
			oldVideo.setGroupId(video.getGroupId());
		}
		if(VodUtil.editVideo(oldVideo)) {
			update(oldVideo);
		}
		return oldVideo;
	}
	
	@Override
	public void deleteByArrayId(String[] idArray) {
		videoMapper.deleteByArrayId(idArray);
	}
	
	@Override
	public void updateByArrayId(Integer groupId, String[] idArray) {
		videoMapper.updateByArrayId(groupId, idArray);
	}
	
	@Override
	public List<Video> list(int eventId, String searchText) {
		List<Video> list = videoMapper.list(eventId, searchText);
		return list;
	}
	
	@Override
	public List<Video> listByGroupId(int groupId, String searchText) {
		List<Video> list = videoMapper.listByGroupId(groupId, searchText);
		return list;
	}
	
	private Video updateVideoFullInfo(Video video, String videoId) {
		Map resultMap = VodUtil.getVideoInfo(videoId);
		while (true) {
			if(resultMap.get("Code")!=null) {
				if(video.getStatus() == null || video.getStatus() == 0) {
					video.setStatus(3);
					//update(video);
				}
				try {
					Thread.sleep(15000);
					resultMap = VodUtil.getVideoInfo(videoId);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}else {
				Map baseMap = (Map) resultMap.get("VideoBase");
				if(baseMap.get("CoverURL")==null) {
					if(video.getStatus()==null||video.getStatus()==0) {
						video.setStatus(3);
						//update(video);
					}
					try {
						Thread.sleep(15000);
						resultMap = VodUtil.getVideoInfo(videoId);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}else {
					break;
				}
			}
		}
		Map odMap = VodUtil.getODVideo(videoId);
		if(video!=null) {
			Map baseMap = (Map) resultMap.get("VideoBase");
			String status = baseMap.get("Status").toString();
			status = status.toUpperCase();
			VideoStatus videoStat = VideoStatus.valueOf(status);
			if(video.getStatus()==3) {
				video.setTime(baseMap.get("Duration").toString());
				video.setHeadImage(baseMap.get("CoverURL").toString());
				video.setStatus(videoStat.ordinal());
				video.setOdAddress(odMap.get("FileURL").toString());
				Map playMap = (Map) resultMap.get("PlayInfoList");
				List<Map> playList = (List<Map>) playMap.get("PlayInfo");
				for(Map map:playList) {
					String Definition = map.get("Definition").toString();
					if(Definition.equals("LD")) {
						video.setLdAddress(map.get("PlayURL").toString());
					}
				}
				update(video);
			}else {
				if(video.getStatus()!=videoStat.ordinal()) {
					video.setStatus(videoStat.ordinal());
					update(video);
				}
			}
		}
		return video;
	}
}
