import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        EditText edNamaDepan = findViewById(R.id.edNamaDepan);
        EditText edNamaBelakang = findViewById(R.id.edNamaBelakang);
        Button btnSimpan = findViewById(R.id.btnSimpan);

        ArrayList<String> daftar_nama = new ArrayList<>();

        Intent intent_list = new Intent(MainActivity.this, ListActivity.class);

        btnSimpan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String isian_nama_depan = edNamaDepan.getText().toString();
                String isian_nama_belakang = edNamaBelakang.getText().toString();

                if (isian_nama_depan.isEmpty()) {
                    Toast.makeText(getApplicationContext(), "Isian masih kosong", Toast.LENGTH_SHORT).show();
                } else {
                    String nama_lengkap = isian_nama_depan.concat(" ").concat(isian_nama_belakang);
                    daftar_nama.clear();
                    daftar_nama.add(nama_lengkap);

                    // Memproses daftar_nama dan mengirimkannya ke aktivitas berikutnya
                    ArrayList<String> daftar_nama_tanpa_genap = new ArrayList<>();
                    for (int i = 0; i < daftar_nama.size(); i++) {
                        if (i % 2 != 0) { // Mengabaikan bilangan genap
                            daftar_nama_tanpa_genap.add("Index: " + i + ", Item: " + daftar_nama.get(i));
                        }
                    }
                    intent_list.putStringArrayListExtra("daftar_nama", daftar_nama_tanpa_genap);

                    // Membersihkan input fields
                    edNamaDepan.setText("");
                    edNamaBelakang.setText("");

                    // Memulai aktivitas berikutnya
                    startActivity(intent_list);
                }
            }
        });
    }
}
