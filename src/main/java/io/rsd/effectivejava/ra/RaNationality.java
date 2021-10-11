package io.rsd.effectivejava.ra;

import java.io.File;
import java.math.BigDecimal;
import java.util.*;

public class RaNationality {
    //    public Map<String,Object> insertKycRaSingleScrHst(MultipartFile multiPartFile, Map<String,Object> paramMap){
//        // 국가위험평가 위험항목 업로드 프로세스
//        // 1. 기존 국가 위험평가 항목 초기화(mergeInto를 통한 데이터 저장이어서 삭제 되는 건들에 대한 항목이 반영이 안되어 초기화 시킴)
//        // 2. 시트별 기초데이터 HST테이블에 mergeInto
//        // 3. 기초데이터로 스코어 계산 및 HST테이블저장
//        // 4. cut-off 점수 검색 및 위험 등급 설정
//        // 5. HST테이블 위험등급 입력
//        // 6. VAR테이블 스코어 및 위험등급 입력
//        Map<String,Object> resultMap = new HashMap<String,Object>();
//        List<Map<String,Object>> dataList = new ArrayList<Map<String,Object>>();
//        Map<String,Map<String,Object>> resKeyMap = new HashMap<String,Map<String,Object>>();
//        Map<String,Object> resMap  = new HashMap<String,Object>();
//        List<Map<String,Object>> resList = new ArrayList<Map<String,Object>>();
//        File convFile = CommonUtil.convertFile(multiPartFile);
//        List<List<String>> headerArrList   = new ArrayList<List<String>>();
//        List<List<String>> codeArrList     = new ArrayList<List<String>>();
//        List<String> headerList = new ArrayList<String>();
//        List<String> codeList = new ArrayList<String>();
//        List<String> sheetNameArr = new ArrayList<String>(Arrays.asList("FATF비협조국","FATF회원국","INCSR","OECD_EU","OFAC","UN","호주외교통상부","국제투명성기구(TI)","바젤 국가경영연구소"));
//        List<String> sheetDataTypeArr = new ArrayList<String>(Arrays.asList("FLAG","FLAG","FLAG","FLAG","FLAG","FLAG","FLAG","MAP","MAP"));
//        String dataType;
//        int sheetLen = 0;
//        int seq      = 0;
//        int shtIdx   = -1;
//        sheetLen = ExcelPoiUtils.getSheetLength(convFile);
//        headerArrList.add(new ArrayList<String>(Arrays.asList("FATF취약국가","FATF비협조 국가")));
//        headerArrList.add(new ArrayList<String>(Arrays.asList("FATF Members","FATF Observers")));
//        headerArrList.add(new ArrayList<String>(Arrays.asList("요주의 지역(Primary Concern)","주의 지역(Concern)","기타모니터링 지역(Monitored)")));
//        headerArrList.add(new ArrayList<String>(Arrays.asList("EU지정 조세회피처","OECD지정 조세회피처(Committed)")));
//        headerArrList.add(new ArrayList<String>(Arrays.asList("OFAC제재국")));
//        headerArrList.add(new ArrayList<String>(Arrays.asList("UN제재국")));
//        headerArrList.add(new ArrayList<String>(Arrays.asList("호주 외교통상부제재국")));
//        headerArrList.add(new ArrayList<String>(Arrays.asList("국가코드","수치(CPI)")));
//        headerArrList.add(new ArrayList<String>(Arrays.asList("국가코드","리스크 점수")));
//        codeArrList.add(new ArrayList<String>(Arrays.asList("01","03")));
//        codeArrList.add(new ArrayList<String>(Arrays.asList("01","02")));
//        codeArrList.add(new ArrayList<String>(Arrays.asList("02","01","03")));
//        codeArrList.add(new ArrayList<String>(Arrays.asList("01","03")));
//        codeArrList.add(new ArrayList<String>(Arrays.asList("1")));
//        codeArrList.add(new ArrayList<String>(Arrays.asList("1")));
//        codeArrList.add(new ArrayList<String>(Arrays.asList("1")));
//        codeArrList.add(new ArrayList<String>(Arrays.asList("KEY","국제투명성기구(TI)")));
//        codeArrList.add(new ArrayList<String>(Arrays.asList("KEY","바젤 국가경영연구소")));
//
//        if(sheetLen > 0){
//            seq = kyc820Mapper.selectKycRaSingleScrHstSeq(paramMap);
//            paramMap.put("SEQ", seq);
//            kyc820Mapper.initKycRaSingleScrHst(paramMap);
//            for(String shtNm : sheetNameArr){
//                shtIdx ++;
//                headerList = headerArrList.get(shtIdx);
//                codeList = codeArrList.get(shtIdx);
//                dataType = sheetDataTypeArr.get(shtIdx);
//                dataList = ExcelPoiUtils.getDataFromExcel(convFile, shtNm, 1, headerList);
//                for(Map<String,Object> data : dataList){ //엑셀 data리스트 로우수만큼 for 문
//                    if("FLAG".equals(dataType)){
//                        for(Map.Entry<String,Object> entry : data.entrySet()){ // 엑셀 컬럼수만큼 for 문
//                            for(String header : headerList){ // 컬럼에 위에 정의한값이 있는지 확인하는 for 문
//                                resMap = resKeyMap.get(String.valueOf(entry.getValue())) != null ? resKeyMap.get(String.valueOf(entry.getValue())) : new HashMap<String,Object>();
//                                if(entry.getValue() != null && header.equals(entry.getKey())){
//                                    resMap.put(shtNm,codeList.get(headerList.indexOf(header)));
//                                }
//                                resKeyMap.put(String.valueOf(entry.getValue()), resMap);
//                            }
//                        }
//                    } else if("MAP".equals(dataType)) {
//                        resMap = resKeyMap.get(String.valueOf(data.get("국가코드"))) != null ? resKeyMap.get(String.valueOf(data.get("국가코드"))) : new HashMap<String,Object>();
//                        for(String header : headerList){
//                            resMap.put(codeList.get(headerList.indexOf(header)), data.get(header));
//                        }
//                        resKeyMap.put(String.valueOf(resMap.get("국가코드")), resMap);
//                    }
//                }
//            }
//            for(Entry<String, Map<String, Object>> entry : resKeyMap.entrySet()){ // 엑셀 컬럼수만큼 for 문
//                resMap = entry.getValue();
//                resMap.put("KEY", entry.getKey());
//                resList.add(resMap);
//            }
//            System.err.println(resList.toString());
//            paramMap.put("NTN_DATA", resList);
//            kyc820Mapper.mergeIntoKycRaSingleScrHst(paramMap);
//            resultMap.put("resultCd", "00");
//        }else{
//            resultMap.put("resultCd", "97");
//        }
//        return resultMap;
//    }
    private enum CountryRisk {
        FATF_RISK(Type.MULTIPLE_CHOICE, "FATF비협조국"),
        FATF_FRIENDLY(Type.MULTIPLE_CHOICE, "FATF회원국"),
        INCSR(Type.FLAG, "INSCR"),
        OECD(Type.FLAG, "OECD_EU"),
        EU(Type.FLAG, "OECD_EU"),
        OFAC(Type.FLAG, "OFAC"),
        UN(Type.FLAG, "UN"),
        AUSTRALIA(Type.FLAG, "호주외교통상부"),
        TI(Type.NUMBER, "국제 투명성기구(TI)"),
        BASEL(Type.NUMBER, "바젤 국가경영연구소");

        private final Type type;
        private final String sheetName;

        CountryRisk(Type type, String sheetName) {
            this.type = type;
            this.sheetName = sheetName;
        }
    }

    private enum Type {
        FLAG,
        MULTIPLE_CHOICE,
        NUMBER,
    }

    private enum FatfRisk {
        HIGH_RISK("01", BigDecimal.valueOf(2), "FATF비협조국가"),
        MONITORING("03", BigDecimal.valueOf(1), "FATF취약국가"),
        NONE("02", BigDecimal.valueOf(0), "");

        private final String code;
        private final BigDecimal weight;
        private final String headerName;

        FatfRisk(String code, BigDecimal weight, String herderName) {
            this.code = code;
            this.weight = weight;
            this.headerName = herderName;
        }
    }

    private enum FatfFriendly {
        MEMBER("01", BigDecimal.valueOf(1)),
        OBSERVER("02", BigDecimal.valueOf(1)),
        NONE("03", BigDecimal.valueOf(0));

        private final String code;
        private final BigDecimal weight;

        FatfFriendly(String code, BigDecimal weight) {
            this.code = code;
            this.weight = weight;
        }
    }
}
