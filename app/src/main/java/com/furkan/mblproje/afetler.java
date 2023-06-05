package com.furkan.mblproje;

import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import java.util.ArrayList;

public class afetler extends AppCompatActivity {
    private ListView afetListView;
    private ArrayList<Afet> afetListesi;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.afetler);

        afetListView = findViewById(R.id.afetListView);

        // Afetler listesi
        afetListesi = new ArrayList<>();
        afetListesi.add(new Afet("Deprem", "Deprem, yer kabuğunda meydana gelen titreşimlerdir.","Sarsıntı Anında:\n" +
                "\n" +
                "Sakin olun ve paniğe kapılmayın.\n" +
                "Eğer içerideyseniz, masa, masa altı, dolap gibi sağlam mobilyaların yanına veya içine geçin ve başınızı koruyun.\n" +
                "Pencere ve cam eşyalardan uzak durun.\n" +
                "Asansörü kullanmayın.\n" +
                "Sarsıntı Sonrası:\n" +
                "\n" +
                "Güvende olduğunuzdan emin olun.\n" +
                "Yaralılara yardım edin ve acil durum hizmetlerine haber verin.\n" +
                "Elektrik, gaz ve su gibi tehlikeli kaynakları kapatın.\n" +
                "Ev veya binanızın güvenli olup olmadığını kontrol edin. Hasarlı veya çökmüş yapıları terk edin.\n" +
                "Açık alanlarda toplanma noktalarına gidin ve sağlıklı haber kaynaklarından bilgi alın.\n" +
                "Acil Durum Hazırlığı:\n" +
                "\n" +
                "Acil durum çantası hazırlayın. Bu çanta içinde temel ihtiyaçları karşılayacak su, yiyecek, ilaçlar, battaniye, el feneri gibi malzemeler bulunmalıdır.\n" +
                "Acil durum planı oluşturun ve ailenizle veya ev arkadaşlarınızla paylaşın. Bu plan, buluşma noktalarını, iletişim bilgilerini ve acil durum iletişim prosedürlerini içermelidir.\n" +
                "Bina veya evinizde güvenlik önlemleri alın. Eşyaları sabitleyin, hasar riski taşıyan yapısal zayıflıkları tespit edin ve onarımlar yapın.\n" +
                "Deprem sigortası ve diğer uygun sigortaları değerlendirin.\n" +
                "Depremden sonra düzenli olarak tatbikatlar yapın ve acil durum planınızı güncel tutun.", R.drawable.deprem));

        afetListesi.add(new Afet("Sel", "Sel, yoğun yağışlar sonucu su birikintilerinin oluşması ve taşmasıdır.",
                "Sel Durumunda Yapılması Gerekenler:\n\n" +
                        "Güvende olduğunuzdan emin olun.\n" +
                        "Sel bölgesinden uzaklaşın ve yüksek yerlere çıkın.\n" +
                        "Akarsular ve dereler gibi su akışı olan yerlerden uzak durun.\n" +
                "Yerel yetkililerin talimatlarını takip edin ve acil durum hizmetlerine haber verin.\n" +
                        "Evinizde veya binanızda güvenli bir kat ve yüksek yerlere çıkın.\n" +
                        "Elektrik, gaz ve su gibi tehlikeli kaynakları kapatın.\n" +
                        "Hayvanlarınızı güvende tutun veya mümkünse onları da tahliye edin.\n" +
                        "Eğer araç kullanıyorsanız, sel sularından uzak durun ve sel sularına kapılmamak için yüksek noktalara çıkın.\n" +
                        "Hasarlı veya çökmüş yapıları terk edin ve yaralılara yardım edin.\n" +
                        "Sel sonrası zararları raporlayın ve sigorta şirketinizle iletişime geçin.", R.drawable.sel));
        afetListesi.add(new Afet("Yangın", "Yangın, kontrolsüz bir şekilde yayılan ateşin neden olduğu felakettir.",
                "Yangın Durumunda Yapılması Gerekenler:\n\n" +
                        "Güvende olduğunuzdan emin olun ve hızlıca yangın alarmını çalıştırın.\n" +
                        "Yangın söndürme cihazlarını kullanarak yangına müdahale edin (yalnızca eğitimli kişiler).\n" +
                        "Yangının yayılmasını engellemek için kapıları ve pencereleri kapatarak oksijen akışını kesin.\n" +
                        "Dumanın yayılmasını önlemek için düşükten yürüyün ve sık sık nefes alın.\n" +
                        "Aşırı duman veya alevlerin olduğu alanlarda kalabalık alanlara yönelin ve acil durum hizmetlerini arayın.\n" +
                        "Eğer giysileriniz alev alırsa, yere yatıp alevleri söndürmek için durup sürüklenin.\n" +
                        "Yüksek binalarda yangın merdivenlerini kullanın ve asansörleri kullanmayın.\n" +
                        "Yangın sonrası zararları raporlayın ve sigorta şirketinizle iletişime geçin.", R.drawable.yangin));
        afetListesi.add(new Afet("Fırtına", "Fırtına, yüksek hızda esen rüzgarlarla beraber gelen hava olayıdır.",
                "Fırtına Durumunda Yapılması Gerekenler:\n\n" +
                        "Kendinizi ve diğer insanları açık alanda korunaklı bir yere taşıyın.\n" +
                        "Ağaçlardan, direklerden ve diğer yüksek nesnelerden uzak durun.\n" +
                        "Evinize veya binalara sığının ve güvenli bir yerde kalın.\n" +
                        "Cam eşyalardan ve pencerelerden uzak durun.\n" +
                        "Aracınızdaysanız, mümkünse güvenli bir yere park edin ve camları kapalı tutun.\n" +
                        "Fırtına sonrası zararları raporlayın ve sigorta şirketinizle iletişime geçin.", R.drawable.firtina));

        afetListesi.add(new Afet("Tsunami", "Tsunami, denizde meydana gelen büyük dalgaların kıyıya ulaşması sonucu oluşan felakettir.",
                "Tsunami Durumunda Yapılması Gerekenler:\n\n" +
                        "Sakin olun ve paniğe kapılmayın.\n" +
                        "Derhal yüksek ve emniyetli bir bölgeye geçin. Mümkünse yüksek katlara çıkın.\n" +
                        "Deniz kıyısından ve plajlardan uzaklaşın.\n" +
                        "Tsunami uyarılarını dinleyin ve yerel yetkililerin talimatlarını takip edin.\n" +
                        "Tsunami sularına kapılmamak için nehir yataklarından, köprülerden ve diğer su geçişlerinden uzak durun.\n" +
                        "Tsunami sonrası zararları raporlayın ve sigorta şirketinizle iletişime geçin.", R.drawable.tsunami));


        afetListesi.add(new Afet("Volkanik Patlama", "Volkanik patlama, yer kabuğundaki volkanlardan çıkan lav, gaz ve külün yüksek basınçla atmosfere yayılmasıdır.",
                "Volkanik Patlama Durumunda Yapılması Gerekenler:\n\n" +
                        "Yakındaki volkanik patlama uyarılarını ve tahliye talimatlarını dinleyin.\n" +
                        "Derhal güvenli bir bölgeye geçin ve volkanik patlama riski taşıyan alanlardan uzak durun.\n" +
                        "Volkanik kül yağışı durumunda, ağız ve burun koruyucu maskeler kullanın.\n" +
                        "Yere yayılan lavlardan ve volkanik akıntılardan uzak durun.\n" +
                        "Tehlikeli gazlardan etkilenmemek için kapalı alanlarda kalın ve havalandırmayı kapatın.\n" +
                        "Volkanik patlama sonrası zararları raporlayın ve yetkililere bildirin.", R.drawable.volkan));

        afetListesi.add(new Afet("Çığ", "Çığ, dağlık bölgelerde kar veya buzun büyük bir hızla kayarak aşağıya doğru hareket etmesidir.",
                "Çığ Durumunda Yapılması Gerekenler:\n\n" +
                        "Sakin olun ve paniğe kapılmayın.\n" +
                        "Hızlıca sığınabileceğiniz bir güvenli bölgeye geçin veya çığ tehlikesi olan alandan uzaklaşın.\n" +
                        "Eğer çığ altında kaldıysanız, yüzeye yakın olacak şekilde hareket edin ve nefes alabileceğiniz bir boşluk yaratmaya çalışın.\n" +
                        "Kendinizi ve diğerleri için acil yardım çağırın ve yetkililere durumu bildirin.\n" +
                        "Çığ sonrası zararları raporlayın ve yetkililere bildirin.", R.drawable.cig));



        afetListesi.add(new Afet("Toprak Kayması", "Toprak kayması, eğimli bölgelerde yer kabuğunun kayarak hareket etmesi sonucu oluşan felakettir.",
                "Toprak Kayması Durumunda Yapılması Gerekenler:\n\n" +
                        "Evinizi veya binanızı hemen terk edin ve güvenli bir bölgeye geçin.\n" +
                        "Toprak kayması riski olan bölgelerde bulunan insanlara acil durumu bildirin ve tahliyelerini sağlayın.\n" +
                        "Dik yamaçlardan, tepelerden ve çöküntü bölgelerinden uzak durun.\n" +
                        "Toprağın çatlaması, kayması veya toprak altı seslerinin duyulması durumunda derhal güvenli bir bölgeye geçin.\n" +
                        "Toprak kayması sonrası yapıları kontrol edin ve hasar tespiti yapın.", R.drawable.toprakkaymasi));

        afetListesi.add(new Afet("Hortum", "Hortum, yüksek hızda dönen ve dar bir alanda yere değen hava sütunudur.",
                "Hortum Durumunda Yapılması Gerekenler:\n\n" +
                        "Hortum uyarısı aldığınızda en yakın sığınak veya korunaklı bir bölgeye geçin.\n" +
                        "Düşük alanlara veya çukurlara sığının, ancak sel riski yoksa su birikintilerinden uzak durun.\n" +
                        "Cam ve kırıcı eşyalardan uzak durun, kapalı alanlarda güvende olmak için sert bir yüzeye sığının.\n" +
                        "Açık alanlarda hortumun yolundan uzaklaşmak için yere yatın ve ellerinizi başınızın üzerine koyun.\n" +
                        "Hortum sonrası zararları raporlayın ve yetkililere bildirin.", R.drawable.hortum));


        // Adapter ile list view veri aktarımı
        AfetlerAdapter adapter = new AfetlerAdapter(this, afetListesi);
        afetListView.setAdapter(adapter);

        // ListView öğesine listener
        afetListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                // Seçilen afet öğesini alır
                Afet secilenAfet = afetListesi.get(position);

                // Detayları göster
                showDetailedInformation(secilenAfet);
            }
        });
    }

    //Afet detayları
    private void showDetailedInformation(Afet afet) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this, R.style.MyAlertDialogStyle);
        builder.setTitle(afet.getAd());
        builder.setMessage(afet.detay());
        builder.setIcon(R.drawable.bilgi);
        builder.setPositiveButton("Tamam", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
                // Tamam düğmesine tıklandığında yapılacak işlem
                dialog.dismiss();
            }
        });
        AlertDialog dialog = builder.create();
        dialog.show();
    }

}