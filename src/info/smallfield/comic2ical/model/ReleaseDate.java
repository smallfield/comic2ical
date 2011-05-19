package info.smallfield.comic2ical.model;

import java.io.Serializable;
import java.util.Date;

import com.google.appengine.api.datastore.Key;

import org.slim3.datastore.Attribute;
import org.slim3.datastore.Model;

@Model(schemaVersion = 1)
public class ReleaseDate implements Serializable {

    private static final long serialVersionUID = 1L;

    @Attribute(primaryKey = true)
    private Key key;

    @Attribute(version = true)
    private Long version;

    private String title;
    private String series;
    private String publisher;
    private Date date;
    private Integer price;


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
        ReleaseDate other = (ReleaseDate) obj;
        if (key == null) {
            if (other.key != null) {
                return false;
            }
        } else if (!key.equals(other.key)) {
            return false;
        }
        return true;
    }

    /**
     * titleを取得します。
     * @return title
     */
    public String getTitle() {
        return title;
    }

    /**
     * titleを設定します。
     * @param title title
     */
    public void setTitle(String title) {
        this.title = title;
    }

    /**
     * seriesを取得します。
     * @return series
     */
    public String getSeries() {
        return series;
    }

    /**
     * seriesを設定します。
     * @param series series
     */
    public void setSeries(String series) {
        this.series = series;
    }

    /**
     * publisherを取得します。
     * @return publisher
     */
    public String getPublisher() {
        return publisher;
    }

    /**
     * publisherを設定します。
     * @param publisher publisher
     */
    public void setPublisher(String publisher) {
        this.publisher = publisher;
    }

    /**
     * dateを取得します。
     * @return date
     */
    public Date getDate() {
        return date;
    }

    /**
     * dateを設定します。
     * @param date date
     */
    public void setDate(Date date) {
        this.date = date;
    }

    /**
     * priceを取得します。
     * @return price
     */
    public Integer getPrice() {
        return price;
    }

    /**
     * priceを設定します。
     * @param price price
     */
    public void setPrice(Integer price) {
        this.price = price;
    }
}
