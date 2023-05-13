package efub.clone.hanatour.domain.tour.dto;

import efub.clone.hanatour.domain.image.domain.Image;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.util.List;
import java.util.stream.Collectors;

@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Builder
@Getter
public class ImageDto {
    
    private Long imageId;
    private String url;

    public ImageDto(Image image) {
        this.imageId = image.getImageId();
        this.url = image.getImageUrl();
    }

    public static ImageDto of(Image image) {
        return ImageDto.builder()
                .imageId(image.getImageId())
                .url(image.getImageUrl())
                .build();
    }

    public static List<ImageDto> toList(List<Image> imageList) {
        return imageList.stream().map(ImageDto::of).collect(Collectors.toList());
    }
}
