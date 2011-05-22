package info.smallfield.comic2ical.model;

import info.smallfield.comic2ical.util.AmazonUtil;
import info.smallfield.comic2ical.util.CommonUtil;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import org.slim3.datastore.Attribute;
import org.slim3.datastore.CreationDate;
import org.slim3.datastore.Datastore;
import org.slim3.datastore.Model;
import org.slim3.datastore.ModificationDate;

import com.google.appengine.api.datastore.Key;

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
    private String author;
    private Date date;
    private Integer price;
    private List<String> titleFlagment;
    private List<String> authorFlagment;
    private String amazonUrl;

    @Attribute(listener = ModificationDate.class)
    private Date updatedAt;

    @Attribute(listener = CreationDate.class)
    private Date createdAt;

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
     *
     * @return title
     */
    public String getTitle() {
        return title;
    }

    /**
     * titleを設定します。
     *
     * @param title
     *            title
     */
    public void setTitle(String title) {
        this.title = title;
        this.setTitleFlagment(CommonUtil.getStringFlagment(title));
    }

    /**
     * seriesを取得します。
     *
     * @return series
     */
    public String getSeries() {
        return series;
    }

    /**
     * seriesを設定します。
     *
     * @param series
     *            series
     */
    public void setSeries(String series) {
        this.series = series;
    }

    /**
     * publisherを取得します。
     *
     * @return publisher
     */
    public String getPublisher() {
        return publisher;
    }

    /**
     * publisherを設定します。
     *
     * @param publisher
     *            publisher
     */
    public void setPublisher(String publisher) {
        this.publisher = publisher;
    }

    /**
     * dateを取得します。
     *
     * @return date
     */
    public Date getDate() {
        return date;
    }

    /**
     * dateを設定します。
     *
     * @param date
     *            date
     */
    public void setDate(Date date) {
        this.date = date;
    }

    /**
     * priceを取得します。
     *
     * @return price
     */
    public Integer getPrice() {
        return price;
    }

    /**
     * priceを設定します。
     *
     * @param price
     *            price
     */
    public void setPrice(Integer price) {
        this.price = price;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
        this.setAuthorFlagment(CommonUtil.getStringFlagment(author));
    }

    public List<String> getTitleFlagment() {
        return titleFlagment;
    }

    public List<String> getAuthorFlagment() {
        return authorFlagment;
    }

    public void setTitleFlagment(List<String> titleFlagment) {
        this.titleFlagment = titleFlagment;
    }

    public void setAuthorFlagment(List<String> authorFlagment) {
        this.authorFlagment = authorFlagment;
    }

    public void setAmazonUrl(String amazonUrl) {
        this.amazonUrl = amazonUrl;
    }

    public String getAmazonUrl() {
        if (this.amazonUrl == null || !(this.amazonUrl.length() > 0)) {
            AmazonUtil au;
            try {
                au = new AmazonUtil();
                this.amazonUrl = au.findItem(this.getTitle());
                Datastore.putAsync(this);
            } catch (Exception e) {
            }
        }
        return this.amazonUrl;
    }

    public void setUpdatedAt(Date updatedAt) {
        this.updatedAt = updatedAt;
    }

    public Date getUpdatedAt() {
        return updatedAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    public Date getCreatedAt() {
        return createdAt;
    }
}
