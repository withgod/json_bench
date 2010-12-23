package withgod.model;

import org.codehaus.jackson.annotate.JsonProperty;

public class UserModel {
    public String name;
    public String profile_sidebar_border_color;
    public String profile_sidebar_fill_color;
    public String profile_image_url;
    public String location;
    public String created_at;
    public String profile_link_color;
    public String url;
    public String lang;
    public String profile_background_color;
    public String description;
    public String time_zone;
    public String profile_background_image_url;
    public String screen_name;
    public String id_str;
    public String follow_request_sent;
    public String profile_text_color;
    public String notifications;
    public String following;
    public String utc_offset;
    public boolean profile_background_tile;
    public boolean is_translator;
    public boolean contributors_enabled;
    public boolean profile_use_background_image;
    public boolean followers_count;
    public boolean geo_enabled;
    public boolean verified;
    public boolean show_all_inline_media;
    @JsonProperty("protected")
    public boolean _protected;
    public long id;
    public int favourites_count;
    public int listed_count;
    public int statuses_count;
    public int friends_count;
}
