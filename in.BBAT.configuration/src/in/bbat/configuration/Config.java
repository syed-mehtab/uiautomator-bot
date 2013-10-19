//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, vJAXB 2.1.10 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2012.07.25 at 12:06:01 PM IST 
//

package in.bbat.configuration;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for anonymous complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType>
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element ref="{}Database"/>
 *         &lt;element ref="{}User"/>
 *         &lt;element ref="{}Licence"/>
 *         &lt;element ref="{}Reports"/>
 *         &lt;element ref="{}Packages"/>
 *         &lt;element ref="{}Campaigns"/>
 *         &lt;element ref="{}Plans"/>
 *         &lt;element ref="{}SwatAgent"/>
 *         &lt;element ref="{}NetworkCtrl"/>
 *         &lt;element ref="{}Phone"/>
 *       &lt;/sequence>
 *       &lt;attribute name="baseDir" use="required" type="{http://www.w3.org/2001/XMLSchema}string" />
 *       &lt;attribute name="testSiteName" use="required" type="{http://www.w3.org/2001/XMLSchema}string" />
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
    "database",
    "user",
    "licence",
     "android"
})
@XmlRootElement(name = "MasterConfig")
public class Config {

    @XmlElement(name = "Database", required = true)
    protected Database database;
    @XmlElement(name = "User", required = true)
    protected User user;
    @XmlElement(name = "Licence", required = true)
    protected Licence licence;
    @XmlElement(name = "Reports", required = true)
    
    protected Phone android;

    
    @XmlAttribute(required = true)
	private String toolVersion;
    
    /**
     * Gets the value of the database property.
     * 
     * @return
     *     possible object is
     *     {@link Database }
     *     
     */
    public Database getDatabase() {
        return database;
    }

    /**
     * Sets the value of the database property.
     * 
     * @param value
     *     allowed object is
     *     {@link Database }
     *     
     */
    public void setDatabase(Database value) {
        this.database = value;
    }

    /**
     * Gets the value of the user property.
     * 
     * @return
     *     possible object is
     *     {@link User }
     *     
     */
    public User getUser() {
        return user;
    }

    /**
     * Sets the value of the user property.
     * 
     * @param value
     *     allowed object is
     *     {@link User }
     *     
     */
    public void setUser(User value) {
        this.user = value;
    }

    /**
     * Gets the value of the licence property.
     * 
     * @return
     *     possible object is
     *     {@link Licence }
     *     
     */
    public Licence getLicence() {
        return licence;
    }

    /**
     * Sets the value of the licence property.
     * 
     * @param value
     *     allowed object is
     *     {@link Licence }
     *     
     */
    public void setLicence(Licence value) {
        this.licence = value;
    }

    /**
     * Gets the value of the reports property.
     * 
     * @return
     *     possible object is
     *     {@link Reports }
     *     
     */
    public Reports getReports() {
        return reports;
    }

    /**
     * Sets the value of the reports property.
     * 
     * @param value
     *     allowed object is
     *     {@link Reports }
     *     
     */
    public void setReports(Reports value) {
        this.reports = value;
    }

    /**
     * Gets the value of the packages property.
     * 
     * @return
     *     possible object is
     *     {@link Packages }
     *     
     */
    public Packages getPackages() {
        return packages;
    }

    /**
     * Sets the value of the packages property.
     * 
     * @param value
     *     allowed object is
     *     {@link Packages }
     *     
     */
    public void setPackages(Packages value) {
        this.packages = value;
    }

    /**
     * Gets the value of the campaigns property.
     * 
     * @return
     *     possible object is
     *     {@link Campaigns }
     *     
     */
    public Campaigns getCampaigns() {
        return campaigns;
    }

    /**
     * Sets the value of the campaigns property.
     * 
     * @param value
     *     allowed object is
     *     {@link Campaigns }
     *     
     */
    public void setCampaigns(Campaigns value) {
        this.campaigns = value;
    }

    /**
     * Gets the value of the plans property.
     * 
     * @return
     *     possible object is
     *     {@link Plans }
     *     
     */
    public Plans getPlans() {
        return plans;
    }

    /**
     * Sets the value of the plans property.
     * 
     * @param value
     *     allowed object is
     *     {@link Plans }
     *     
     */
    public void setPlans(Plans value) {
        this.plans = value;
    }

    /**
     * Gets the value of the swatAgent property.
     * 
     * @return
     *     possible object is
     *     {@link SwatAgent }
     *     
     */
    public SwatAgent getSwatAgent() {
        return swatAgent;
    }

    /**
     * Sets the value of the swatAgent property.
     * 
     * @param value
     *     allowed object is
     *     {@link SwatAgent }
     *     
     */
    public void setSwatAgent(SwatAgent value) {
        this.swatAgent = value;
    }

    /**
     * Gets the value of the networkCtrl property.
     * 
     * @return
     *     possible object is
     *     {@link NetworkCtrl }
     *     
     */
    public NetworkCtrl getNetworkCtrl() {
        return networkCtrl;
    }

    /**
     * Sets the value of the networkCtrl property.
     * 
     * @param value
     *     allowed object is
     *     {@link NetworkCtrl }
     *     
     */
    public void setNetworkCtrl(NetworkCtrl value) {
        this.networkCtrl = value;
    }

    /**
     * Gets the value of the phone property.
     * 
     * @return
     *     possible object is
     *     {@link Phone }
     *     
     */
    public Phone getPhone() {
        return phone;
    }

    /**
     * Sets the value of the phone property.
     * 
     * @param value
     *     allowed object is
     *     {@link Phone }
     *     
     */
    public void setPhone(Phone value) {
        this.phone = value;
    }

    /**
     * Gets the value of the baseDir property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getBaseDir() {
        return baseDir;
    }

    /**
     * Sets the value of the baseDir property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setBaseDir(String value) {
        this.baseDir = value;
    }

    /**
     * Gets the value of the testSiteName property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getTestSiteName() {
        return testSiteName;
    }

    /**
     * Sets the value of the testSiteName property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setTestSiteName(String value) {
        this.testSiteName = value;
    }

	public Http getHttp() {
		return http;
	}

	public void setHttp(Http http) {
		this.http = http;
	}

	public Sip getSip() {
		return sip;
	}

	public void setSip(Sip sip) {
		this.sip = sip;
	}

	public String getToolVersion() {
		return toolVersion;
	}

	public void setToolVersion(String toolVersion) {
		this.toolVersion = toolVersion;
	}

	public String getAdmin() {
		return admin;
	}

	public void setAdmin(String admin) {
		this.admin = admin;
	}

}
