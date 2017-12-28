
package com.rl.ecps.stu;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the com.rl.ecps.stu package. 
 * <p>An ObjectFactory allows you to programatically 
 * construct new instances of the Java representation 
 * for XML content. The Java representation of XML 
 * content can consist of schema derived interfaces 
 * and classes representing the binding of schema 
 * type definitions, element declarations and model 
 * groups.  Factory methods for each of these are 
 * provided in this class.
 * 
 */
@XmlRegistry
public class ObjectFactory {

    private final static QName _PublishItemResponse_QNAME = new QName("http://webservice.ecps.rl.com/", "publishItemResponse");
    private final static QName _PublishItem_QNAME = new QName("http://webservice.ecps.rl.com/", "publishItem");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: com.rl.ecps.stu
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link PublishItem }
     * 
     */
    public PublishItem createPublishItem() {
        return new PublishItem();
    }

    /**
     * Create an instance of {@link PublishItemResponse }
     * 
     */
    public PublishItemResponse createPublishItemResponse() {
        return new PublishItemResponse();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link PublishItemResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://webservice.ecps.rl.com/", name = "publishItemResponse")
    public JAXBElement<PublishItemResponse> createPublishItemResponse(PublishItemResponse value) {
        return new JAXBElement<PublishItemResponse>(_PublishItemResponse_QNAME, PublishItemResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link PublishItem }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://webservice.ecps.rl.com/", name = "publishItem")
    public JAXBElement<PublishItem> createPublishItem(PublishItem value) {
        return new JAXBElement<PublishItem>(_PublishItem_QNAME, PublishItem.class, null, value);
    }

}
