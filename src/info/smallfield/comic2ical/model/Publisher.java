package info.smallfield.comic2ical.model;

import info.smallfield.comic2ical.meta.SeriesMeta;
import info.smallfield.comic2ical.util.CommonUtil;

import java.io.Serializable;
import java.util.List;

import org.slim3.datastore.Attribute;
import org.slim3.datastore.InverseModelListRef;
import org.slim3.datastore.Model;

import com.google.appengine.api.datastore.Key;

@Model(schemaVersion = 1)
public class Publisher implements Serializable {

    private static final long serialVersionUID = 1L;

    @Attribute(primaryKey = true)
    private Key key;

    @Attribute(version = true)
    private Long version;

    private String name;
    private List<String> nameFlagment;

    @Attribute(persistent = false)
    private InverseModelListRef<Series, Publisher> seriesListRef =
        new InverseModelListRef<Series, Publisher>(
            Series.class,
            SeriesMeta.get().publisherRef,
            this);

    /**
     * Returns the key.
     *
     * @return the key
     */
    public Key getKey() {
        return key;
    }

    /**
     * Sets the key.
     *
     * @param key
     *            the key
     */
    public void setKey(Key key) {
        this.key = key;
    }

    /**
     * Returns the version.
     *
     * @return the version
     */
    public Long getVersion() {
        return version;
    }

    /**
     * Sets the version.
     *
     * @param version
     *            the version
     */
    public void setVersion(Long version) {
        this.version = version;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((key == null) ? 0 : key.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        Publisher other = (Publisher) obj;
        if (key == null) {
            if (other.key != null) {
                return false;
            }
        } else if (!key.equals(other.key)) {
            return false;
        }
        return true;
    }

    public void setName(String name) {
        this.name = name;
        this.setNameFlagment(CommonUtil.getStringFlagment(name));
    }

    public String getName() {
        return name;
    }

    public InverseModelListRef<Series, Publisher> getSeriesListRef() {
        return seriesListRef;
    }

    public void setNameFlagment(List<String> nameFlagment) {
        this.nameFlagment = nameFlagment;
    }

    public List<String> getNameFlagment() {
        return nameFlagment;
    }
}
