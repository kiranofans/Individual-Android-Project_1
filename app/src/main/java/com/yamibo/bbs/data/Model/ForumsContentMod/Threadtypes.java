
package com.yamibo.bbs.data.Model.ForumsContentMod;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Threadtypes {

    @SerializedName("required")
    @Expose
    private String required;
    @SerializedName("listable")
    @Expose
    private String listable;
    @SerializedName("prefix")
    @Expose
    private String prefix;
    @SerializedName("types")
    @Expose
    private Types types;
    @SerializedName("groupIconId")
    @Expose
    private GroupIconId groupIconId;
    @SerializedName("moderators")
    @Expose
    private Moderators moderators;

    public String getRequired() {
        return required;
    }

    public void setRequired(String required) {
        this.required = required;
    }

    public String getListable() {
        return listable;
    }

    public void setListable(String listable) {
        this.listable = listable;
    }

    public String getPrefix() {
        return prefix;
    }

    public void setPrefix(String prefix) {
        this.prefix = prefix;
    }

    public Types getTypes() {
        return types;
    }

    public void setTypes(Types types) {
        this.types = types;
    }

    public GroupIconId getGroupIconId() {
        return groupIconId;
    }

    public void setGroupIconId(GroupIconId groupIconId) {
        this.groupIconId = groupIconId;
    }

    public Moderators getModerators() {
        return moderators;
    }

    public void setModerators(Moderators moderators) {
        this.moderators = moderators;
    }

}
