function formatValidator(type, target) {
    let alphabetReg = /[a-zA-Z]/;
    let numberReg = /[0-9]/g;
    let idSpecCharReg = /[\{\}\[\]\/?.,;:|\)*~`!^\-+<>@\#$%&\\\=\(\'\"]/g;
    let korConsonantsVowelsReg = /[ㄱ-ㅎ|ㅏ-ㅣ]/;
    let korFullReg = /[가-힣]/;

    if (type === "ID") {
        // if (alphabetReg.test(target.substring(0, 1))) {
        if (alphabetReg.test(target) || numberReg.test(target)) {
            if (!idSpecCharReg.test(target) && !korConsonantsVowelsReg.test(target))
                return false;
        }
        // else
        //     console.log("첫 번째 문자는 영문자이어야 합니다.");
        // }
        return true;
    } else if (type === "NAME") {
        if (alphabetReg.test(target) || numberReg.test(target) || korFullReg.test(target)) {
            if (!idSpecCharReg.test(target) && !korConsonantsVowelsReg.test(target))
                return false;
        }
        return true;
    }
}

const jsRedirect = function (url) {
    location.href = url;
}