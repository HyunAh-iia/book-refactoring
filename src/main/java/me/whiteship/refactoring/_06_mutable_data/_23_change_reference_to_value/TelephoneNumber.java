package me.whiteship.refactoring._06_mutable_data._23_change_reference_to_value;

// Java 14 이후부터는 Record를 사용하면 된다 (17이 LTS 됐으니까 토이프로젝트에서 해보면 좋겠다)
public record TelephoneNumber(String areaCode, String number) {
}
