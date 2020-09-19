SHOPPING CARD DEVELOPMENT

Bu uygulama SpringBoot web service olarak geli?tirilmi? olup, a?a??daki teknolojiler kullan?lm??t?r:

* Java 8
* Jpa
* RestAPI
* Lombok
* mySQL
* Junit
* Mockito
* AssertJ
* Swagger-UI
* Log4J

Uygulaman?n data modeli ?u ?ekildedir:



CARD: Sepetin ücret bilgilerinin tutuldu?u ana tablodur.

CARD_PRODUCT: Sepete eklenmi? olan ürün ile ili?kiyi sa?layan tablodur. Quantity alan?nda tutulan de?er kadar, ilgili üründen sepete eklenmi?tir.

PRODUCT: Ürünün ad?n?n, birim ücretinin, ürünün markas?n?(firm_name) ve ba?l? oldu?u kategorinin id’sini tutar.

CATEGORY: Kategori temel bilgilerini tutar. Parent_category_id ile recursive ?ekilde üst kategorilere ula??labilir. Kategorinin ba?l? bulundu?u kampanya campaign_id kolonu üzerinden foreign_key ili?kisi içerisinde eri?ilebilir.

CAMPAIGN: Kampanya bilgilerinin tutuldu?u tablodur. Kampanya ba?lang?ç ve biti? tarihleri kontrol edilerek, kampanyan?n aktif olup olmad??? anla??labilir.

CAMPAIGN_DISCOUNT: Sepete eklenen ürün say?s?na göre indirimin stratejisinin tutuldu?u tablodur. Discount_type alan? RATE ise, yani kampanyada oransal indirim uygulanacaksa, product_count (ilgili kampanyaya ba?l? sepetteki ürün say?s?na) alan?na göre, indirimde oransal hesaplama yap?l?r. Discount_type alan? AMOUNT ise, product_count say?s?na göre indirimde tutar hesaplamas? yap?l?r. 3’ten fazla ürün için product_count sütununda -1 de?eri tutulur.

COUPON: Sistemde mevcut indirim kuponlar?n?n tutuldu?u tablodur. Code alan?nda kuponun kodu, discount_amount alan?nda indirim tutar? ve min_card_amount alan?nda, kullan?lmas? için sepet ücretinin minimum tutar? tutulur. Coupon_status alan? ACTIVE veya USED olabilir.

CARD_COUPON: Sepette indirimi kulland?r?lm?? kuponlar?n tutuldu?u tablodur. Usage_date alan?, kuponun sepete eklendi?i zaman? tutar.


UYGULAMA KURULUMU
Uygulama java-8 üzerinde çal??maktad?r. Lokal ortamda kullanmak için makinenizde Java-8, mySQL ve Lombok kurulumlar?n?n yap?lm?? olmas? gerekmektedir.

Free-licence oldu?u için mySQL veritaban? kullan?lm??t?r. Entity düzeyinde mapleme ve jpa kullan?ld??? için uygulama veritaban? ba??ms?z çal??maktad?r.

Veritaban?n? de?i?tirmek isterseniz, proje içerisinde bulunan application.properties dosyan?nda veritaban? eri?im bilgilerini de?i?tirmeniz yeterli olacakt?r.

Uygulamay? kullanmak için etrade ad?nda bir veritaban? yaratman?z gerekmektedir. Veritaban? ismini de?i?tirmek için yine application.properties dosyas?nda de?i?iklik yapabilirsiniz.


BACK-END MODEL?

Back-end modeli restful web service olarak tasarlanm??t?r. Uygulama için gerekli servislere CardApiController üzerinden eri?ilebilir.

Her bir veritaban? tablosu için bir entity olu?turulmu?tur. Örnek: Card.java, Product.java….

Her bir entity’e eri?im sprint data ile repository class’lar? üzerinden sa?lanmaktad?r. Örnek: CardRepository.java, ProductRepository.java

Parasal veri tipleri Money class’? ile modellenmi?tir. 
Her bir tablonun alanlar? unique olarak generate edilmi? olup, ayn? anda tabloya eri?imlerde verinin bütünlü?ünü korumak için version alan? da tablolara eklenmi?tir. Her bir entity IdVersion abstract class’?n? extend ederek, tüm tablolar?n ayn? ?ekilde davranmas? sa?lanm??t?r.

Her modelin üzerinde ba??ms?z i?lemleri yapan Command ve Query servisleri çal??maktad?r.Command servisler transactional olup, transaction bütünlü?ünün korunmas?n?n zorunlu oldu?u create, update, delete i?lemlerini ça??ran methodlar? içermektedir. Query servisleri ise transactional olmay?p, sadece okuma amaçl? i?lemleri yapan servislerdir.

Hem kampanya indirimi hem de kargo ücreti hesaplamalar?, ürün adedi, ürün kategorisi, farkl? firmalar?n ürünlerinin sepete eklenebilmesiyle de?i?kenlik gösterebildi?i için Strategy Pattern’? ile hesaplanm??t?r.
(Bak?n?z: IDeliveryStrategy, IDiscountStrategy)

Hata mesajlar? için custom exception handling yap?lm??t?r.

Alan zorunluluklar? Annotation’lar ile sa?lanm??t?r.

Parasal hesaplama i?lemleri MoneyDTO içerisinde encapsulate edilmi?tir.

Kur çevrimleri için ExchangeUtil class’? olu?turulmu?tur.

Statü ve tip alanlar? için enum de?erleri kullan?lm??t?r.

Projenin d??ar? aç?ld???, CardApiController’dan DTO objeleri olarak servis sonuçlar? döndürülmü?, data objelerinin d??ar?dan eri?imi engellenmi?tir.

Loglama için log4J Logger s?n?f? kullan?lm??t?r.

UNIT TEST

Unit testler junit ile yaz?lm??t?r. Coverage oranlar? a?a??daki gibidir:







ÖRNEK UYGULAMA KULLANIMI

Uygulama server’I aya?a kalkt???nda resources alt?nda bulunan insert_sql otomatik olarak çal??arak, gerekli verileri tablolara doldurmaktad?r.

Uygulama swagger-ui ile, localhost:8080/swagger-ui.html sayfas?ndan a?a??daki ?ekilde aç?labilir.



Burada API’deki ilgili servisleri ça??rabilirsiniz.

/shopping/test servisi ile Console’dan ya da log dosyalar?ndan a?a??daki ?ekilde i?lem sonuçlar?n? görebilirsiniz. 




