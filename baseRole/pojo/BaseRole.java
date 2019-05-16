package com.qhit.baseRole.pojo;




public class BaseRole {

    private Integer rid;    //角色ID
    private String rname;    //角色名称

    public Integer getRid() { 
        return rid;
    }
 
    public void setRid(Integer rid) { 
        this.rid = rid;
    }
 
    public String getRname() { 
        return rname;
    }
 
    public void setRname(String rname) { 
        this.rname = rname;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        BaseRole baseRole = (BaseRole) o;

        if (rid != null ? !rid.equals(baseRole.rid) : baseRole.rid != null) return false;
        return rname != null ? rname.equals(baseRole.rname) : baseRole.rname == null;
    }

    @Override
    public int hashCode() {
        int result = rid != null ? rid.hashCode() : 0;
        result = 31 * result + (rname != null ? rname.hashCode() : 0);
        return result;
    }


}