package converters;

//@Converter(autoApply = true)
//public class DateAttributeConverter implements AttributeConverter<LocalDate, Date> {
//   @Override
//   public Date convertToDatabaseColumn(LocalDate locDate) {
//      return (locDate == null ? null : Date.valueOf(locDate));
//   }
//
//   @Override
//   public LocalDate convertToEntityAttribute(Date sqlDate) {
//      return (sqlDate == null ? null : sqlDate.toLocalDate());
//   }
//}