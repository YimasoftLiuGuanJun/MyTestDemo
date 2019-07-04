package com.suimo.loi.myfamily.mvp.model;

import com.google.gson.annotations.SerializedName;
import com.suimo.loi.myfamily.mvp.model.entity.Gank;

import java.util.List;

/**
 * Created by bob on 17-5-1.
 */

public class GankData extends BaseData {
        public List<String> category;
        public Result results;

        public class Result{
                @SerializedName("Android")
                public List<Gank> androidList;
                @SerializedName("休息视频")
                public List<Gank> restVideoList;
                @SerializedName("iOS")
                public List<Gank> iosList;
                @SerializedName("福利")
                public List<Gank> meiZiList;
                @SerializedName("拓展资源")
                public List<Gank> extendResourceList;
                @SerializedName("瞎推荐")
                public List<Gank> suggestionList;
                @SerializedName("App")
                public List<Gank> appList;
                @SerializedName("前端")
                public List<Gank> webList;
        }
}
