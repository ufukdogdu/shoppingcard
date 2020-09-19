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



CARD: Sepetin �cret bilgilerinin tutuldu?u ana tablodur.

CARD_PRODUCT: Sepete eklenmi? olan �r�n ile ili?kiyi sa?layan tablodur. Quantity alan?nda tutulan de?er kadar, ilgili �r�nden sepete eklenmi?tir.

PRODUCT: �r�n�n ad?n?n, birim �cretinin, �r�n�n markas?n?(firm_name) ve ba?l? oldu?u kategorinin id�sini tutar.

CATEGORY: Kategori temel bilgilerini tutar. Parent_category_id ile recursive ?ekilde �st kategorilere ula??labilir. Kategorinin ba?l? bulundu?u kampanya campaign_id kolonu �zerinden foreign_key ili?kisi i�erisinde eri?ilebilir.

CAMPAIGN: Kampanya bilgilerinin tutuldu?u tablodur. Kampanya ba?lang?� ve biti? tarihleri kontrol edilerek, kampanyan?n aktif olup olmad??? anla??labilir.

CAMPAIGN_DISCOUNT: Sepete eklenen �r�n say?s?na g�re indirimin stratejisinin tutuldu?u tablodur. Discount_type alan? RATE ise, yani kampanyada oransal indirim uygulanacaksa, product_count (ilgili kampanyaya ba?l? sepetteki �r�n say?s?na) alan?na g�re, indirimde oransal hesaplama yap?l?r. Discount_type alan? AMOUNT ise, product_count say?s?na g�re indirimde tutar hesaplamas? yap?l?r. 3�ten fazla �r�n i�in product_count s�tununda -1 de?eri tutulur.

COUPON: Sistemde mevcut indirim kuponlar?n?n tutuldu?u tablodur. Code alan?nda kuponun kodu, discount_amount alan?nda indirim tutar? ve min_card_amount alan?nda, kullan?lmas? i�in sepet �cretinin minimum tutar? tutulur. Coupon_status alan? ACTIVE veya USED olabilir.

CARD_COUPON: Sepette indirimi kulland?r?lm?? kuponlar?n tutuldu?u tablodur. Usage_date alan?, kuponun sepete eklendi?i zaman? tutar.


UYGULAMA KURULUMU
Uygulama java-8 �zerinde �al??maktad?r. Lokal ortamda kullanmak i�in makinenizde Java-8, mySQL ve Lombok kurulumlar?n?n yap?lm?? olmas? gerekmektedir.

Free-licence oldu?u i�in mySQL veritaban? kullan?lm??t?r. Entity d�zeyinde mapleme ve jpa kullan?ld??? i�in uygulama veritaban? ba??ms?z �al??maktad?r.

Veritaban?n? de?i?tirmek isterseniz, proje i�erisinde bulunan application.properties dosyan?nda veritaban? eri?im bilgilerini de?i?tirmeniz yeterli olacakt?r.

Uygulamay? kullanmak i�in etrade ad?nda bir veritaban? yaratman?z gerekmektedir. Veritaban? ismini de?i?tirmek i�in yine application.properties dosyas?nda de?i?iklik yapabilirsiniz.


BACK-END MODEL?

Back-end modeli restful web service olarak tasarlanm??t?r. Uygulama i�in gerekli servislere CardApiController �zerinden eri?ilebilir.

Her bir veritaban? tablosu i�in bir entity olu?turulmu?tur. �rnek: Card.java, Product.java�.

Her bir entity�e eri?im sprint data ile repository class�lar? �zerinden sa?lanmaktad?r. �rnek: CardRepository.java, ProductRepository.java

Parasal veri tipleri Money class�? ile modellenmi?tir. 
Her bir tablonun alanlar? unique olarak generate edilmi? olup, ayn? anda tabloya eri?imlerde verinin b�t�nl�?�n� korumak i�in version alan? da tablolara eklenmi?tir. Her bir entity IdVersion abstract class�?n? extend ederek, t�m tablolar?n ayn? ?ekilde davranmas? sa?lanm??t?r.

Her modelin �zerinde ba??ms?z i?lemleri yapan Command ve Query servisleri �al??maktad?r.Command servisler transactional olup, transaction b�t�nl�?�n�n korunmas?n?n zorunlu oldu?u create, update, delete i?lemlerini �a??ran methodlar? i�ermektedir. Query servisleri ise transactional olmay?p, sadece okuma ama�l? i?lemleri yapan servislerdir.

Hem kampanya indirimi hem de kargo �creti hesaplamalar?, �r�n adedi, �r�n kategorisi, farkl? firmalar?n �r�nlerinin sepete eklenebilmesiyle de?i?kenlik g�sterebildi?i i�in Strategy Pattern�? ile hesaplanm??t?r.
(Bak?n?z: IDeliveryStrategy, IDiscountStrategy)

Hata mesajlar? i�in custom exception handling yap?lm??t?r.

Alan zorunluluklar? Annotation�lar ile sa?lanm??t?r.

Parasal hesaplama i?lemleri MoneyDTO i�erisinde encapsulate edilmi?tir.

Kur �evrimleri i�in ExchangeUtil class�? olu?turulmu?tur.

Stat� ve tip alanlar? i�in enum de?erleri kullan?lm??t?r.

Projenin d??ar? a�?ld???, CardApiController�dan DTO objeleri olarak servis sonu�lar? d�nd�r�lm�?, data objelerinin d??ar?dan eri?imi engellenmi?tir.

Loglama i�in log4J Logger s?n?f? kullan?lm??t?r.

UNIT TEST

Unit testler junit ile yaz?lm??t?r. Coverage oranlar? a?a??daki gibidir:







�RNEK UYGULAMA KULLANIMI

Uygulama server�I aya?a kalkt???nda resources alt?nda bulunan insert_sql otomatik olarak �al??arak, gerekli verileri tablolara doldurmaktad?r.

Uygulama swagger-ui ile, localhost:8080/swagger-ui.html sayfas?ndan a?a??daki ?ekilde a�?labilir.



Burada API�deki ilgili servisleri �a??rabilirsiniz.

/shopping/test servisi ile Console�dan ya da log dosyalar?ndan a?a??daki ?ekilde i?lem sonu�lar?n? g�rebilirsiniz. 




