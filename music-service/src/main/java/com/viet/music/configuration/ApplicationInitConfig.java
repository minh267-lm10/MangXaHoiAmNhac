package com.viet.music.configuration;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.viet.music.entity.Playlist;
import com.viet.music.entity.Song;
import com.viet.music.repository.PlaylistRepository;
import com.viet.music.repository.SongRepository;

@Configuration
public class ApplicationInitConfig {
    @Bean
    @ConditionalOnProperty(
            prefix = "spring",
            value = "data.mongodb.uri",
            havingValue = "mongodb://root:root@localhost:27017/music-service?authSource=admin")
    ApplicationRunner applicationRunner(SongRepository songRepository, PlaylistRepository playlistRepository) {

        return args -> {
            if (!songRepository.existsByName("Bao Giờ Lấy Chồng")) {
                // Bài hát
                Song songBGLC = Song.builder()
                        .id("1")
                        .name("Bao Giờ Lấy Chồng")
                        .file(
                                "http://localhost:8888/api/v1/music/data/stream/BaoGioLayChongMRKVT2018Remix-DJ-5385923.mp3")
                        .image("http://localhost:8888/api/v1/music/data/img/anh1.jpg")
                        .artistIds(new ArrayList<>(Arrays.asList("3")))
                        .build(); // Bích Phương

                Song songHTCA = Song.builder()
                        .id("2")
                        .name("Hãy Trao Cho Anh")
                        .file(
                                "http://localhost:8888/api/v1/music/data/stream/HayTraoChoAnh-SonTungMTPSnoopDogg-6010660.mp3")
                        .image("http://localhost:8888/api/v1/music/data/img/anh1.jpg")
                        .artistIds(new ArrayList<>(Arrays.asList("1")))
                        .build();

                Song songMTCE = Song.builder()
                        .id("3")
                        .name("Mặt Trời Của Em")
                        .file(
                                "http://localhost:8888/api/v1/music/data/stream/MatTroiCuaEmKynbbRemix-JustaTeePhuongLy-5290457.mp3")
                        .image("http://localhost:8888/api/v1/music/data/img/anh1.jpg")
                        .artistIds(new ArrayList<>(Arrays.asList("2")))
                        .build(); // Phương Ly

                Song songMNMBA = Song.builder()
                        .id("4")
                        .name("Một Năm Mới Bình An")
                        .file("http://localhost:8888/api/v1/music/data/stream/MotNamMoiBinhAn-SonTungMTP-4315569.mp3")
                        .image("http://localhost:8888/api/v1/music/data/img/anh1.jpg")
                        .artistIds(new ArrayList<>(Arrays.asList("1")))
                        .build();

                Song songNNCA = Song.builder()
                        .id("5")
                        .name("Nơi Này Có Anh")
                        .file("data/stream/NoiNayCoAnhTropicalHouseRemix-SonTungMTP-4773696.mp3")
                        .image("http://localhost:8888/api/v1/music/data/img/anh1.jpg")
                        .artistIds(new ArrayList<>(Arrays.asList("1")))
                        .build();

                Song songCCBQ = Song.builder()
                        .id("6")
                        .name("Chuyện Cũ Bỏ Qua")
                        .file("http://localhost:8888/api/v1/music/data/stream/ChuyenCuBoQua_pyelss0ioh.mp3")
                        .image("http://localhost:8888/api/v1/music/data/img/anh1.jpg")
                        .artistIds(new ArrayList<>(Arrays.asList("3")))
                        .build(); // Bích  Phương

                Song songTCNNT = Song.builder()
                        .id("7")
                        .name("Từ Chối Nhẹ Nhàng Thôi")
                        .file(
                                "http://localhost:8888/api/v1/music/data/stream/TuChoiNheNhangThoi-BichPhuongPhucDu-6281296.mp3")
                        .image("http://localhost:8888/api/v1/music/data/img/anh1.jpg")
                        .artistIds(new ArrayList<>(Arrays.asList("3")))
                        .build(); // Bích Phương

                Song songTD = Song.builder()
                        .id("8")
                        .name("Thằng Điên")
                        .file(
                                "http://localhost:8888/api/v1/music/data/stream/ThangDienLive-JustaTeePhuongLy-6066987.mp3")
                        .image("http://localhost:8888/api/v1/music/data/img/anh1.jpg")
                        .artistIds(new ArrayList<>(Arrays.asList("2", "4")))
                        .build(); // JustaTee và PhươngLy

                Song songADSVAT = Song.builder()
                        .id("9")
                        .name("Anh Đã Sai Vì Anh Tin")
                        .file("http://localhost:8888/api/v1/music/data/stream/AnhDaSaiViAnhTin-MrSiro-4694386.mp3")
                        .image("http://localhost:8888/api/v1/music/data/img/anh1.jpg")
                        .artistIds(new ArrayList<>(Arrays.asList("5")))
                        .build(); // Mr.siro

                Song songYNKTY = Song.builder()
                        .id("10")
                        .name("Yêu Người Không Thể Yêu")
                        .file(
                                "http://localhost:8888/api/v1/music/data/stream/YeuNguoiKhongTheYeuCover-MrSiro-5049892.mp3")
                        .image("http://localhost:8888/api/v1/music/data/img/anh1.jpg")
                        .artistIds(new ArrayList<>(Arrays.asList("5")))
                        .build(); // Mr.siro

                Song songTYCV = Song.builder()
                        .id("11")
                        .name("Tình Yêu Chắp Vá")
                        .file("http://localhost:8888/api/v1/music/data/stream/TinhYeuChapVa-MrSiro-4350275.mp3")
                        .image("http://localhost:8888/api/v1/music/data/img/anh1.jpg")
                        .artistIds(new ArrayList<>(Arrays.asList("5")))
                        .build(); // Mr.siro

                Song songCE = Song.builder()
                        .id("12")
                        .name("Cho Em")
                        .file("http://localhost:8888/api/v1/music/data/stream/ChoEm-MrSiro-3254801.mp3")
                        .image("http://localhost:8888/api/v1/music/data/img/anh1.jpg")
                        .artistIds(new ArrayList<>(Arrays.asList("5")))
                        .build(); // Mr.siro

                List<Song> songs = new ArrayList<>(Arrays.asList(
                        songBGLC,
                        songHTCA,
                        songMTCE,
                        songMNMBA,
                        songNNCA,
                        songCCBQ,
                        songTCNNT,
                        songTD,
                        songADSVAT,
                        songYNKTY,
                        songTYCV,
                        songCE));
                songRepository.saveAll(() -> songs.iterator());

                // Playlist
                Playlist playlist1 = Playlist.builder()
                        .id("1")
                        .name("50 bài hát Việt Nam")
                        .image(
                                "http://localhost:8888/api/v1/music/data/img/432994336_4401343820091471_2698424304376113830_n.jpg")
                        .userId("0")
                        .build();

                Playlist playlist2 = Playlist.builder()
                        .id("2")
                        .name("Đề xuất cho bạn")
                        .image(
                                "http://localhost:8888/api/v1/music/data/img/432994336_4401343820091471_2698424304376113830_n.jpg")
                        .userId("0")
                        .build();

                Playlist playlist3 = Playlist.builder()
                        .id("3")
                        .name("Tập tất cả các bài hát")
                        .image(
                                "http://localhost:8888/api/v1/music/data/img/432994336_4401343820091471_2698424304376113830_n.jpg")
                        .userId("0")
                        .build();

                Playlist playlist4 = Playlist.builder()
                        .id("4")
                        .name("Chủ đề mùa xuân")
                        .image(
                                "http://localhost:8888/api/v1/music/data/img/432994336_4401343820091471_2698424304376113830_n.jpg")
                        .userId("0")
                        .build();

                Playlist playlist5 = Playlist.builder()
                        .id("5")
                        .name("Chủ đề suy")
                        .image(
                                "http://localhost:8888/api/v1/music/data/img/432994336_4401343820091471_2698424304376113830_n.jpg")
                        .userId("0")
                        .build();

                List<Playlist> playlists =
                        new ArrayList<>(Arrays.asList(playlist1, playlist2, playlist3, playlist4, playlist5));
                playlistRepository.saveAll(() -> playlists.iterator());
            }
        };
    }
}
