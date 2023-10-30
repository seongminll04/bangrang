package com.ssafy.bangrang.domain.member.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QAppMember is a Querydsl query type for AppMember
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QAppMember extends EntityPathBase<AppMember> {

    private static final long serialVersionUID = 1093396385L;

    public static final QAppMember appMember = new QAppMember("appMember");

    public final QMember _super = new QMember(this);

    //inherited
    public final StringPath accessToken = _super.accessToken;

    public final ListPath<com.ssafy.bangrang.global.fcm.entity.Alarm, com.ssafy.bangrang.global.fcm.entity.QAlarm> alarms = this.<com.ssafy.bangrang.global.fcm.entity.Alarm, com.ssafy.bangrang.global.fcm.entity.QAlarm>createList("alarms", com.ssafy.bangrang.global.fcm.entity.Alarm.class, com.ssafy.bangrang.global.fcm.entity.QAlarm.class, PathInits.DIRECT2);

    public final EnumPath<com.ssafy.bangrang.domain.member.model.vo.AlarmReceivedStatus> allAlarmStatus = createEnum("allAlarmStatus", com.ssafy.bangrang.domain.member.model.vo.AlarmReceivedStatus.class);

    public final EnumPath<com.ssafy.bangrang.domain.member.model.vo.AppMemberStatus> appMemberStatus = createEnum("appMemberStatus", com.ssafy.bangrang.domain.member.model.vo.AppMemberStatus.class);

    public final DateTimePath<java.time.LocalDateTime> deletedAt = createDateTime("deletedAt", java.time.LocalDateTime.class);

    public final StringPath email = createString("email");

    public final EnumPath<com.ssafy.bangrang.domain.member.model.vo.AlarmReceivedStatus> eventAlarmStatus = createEnum("eventAlarmStatus", com.ssafy.bangrang.domain.member.model.vo.AlarmReceivedStatus.class);

    public final StringPath firebaseToken = createString("firebaseToken");

    public final ListPath<Friendship, QFriendship> friendships = this.<Friendship, QFriendship>createList("friendships", Friendship.class, QFriendship.class, PathInits.DIRECT2);

    //inherited
    public final NumberPath<Long> idx = _super.idx;

    public final StringPath imgUrl = createString("imgUrl");

    public final ListPath<com.ssafy.bangrang.domain.inquiry.entity.Inquiry, com.ssafy.bangrang.domain.inquiry.entity.QInquiry> inquiries = this.<com.ssafy.bangrang.domain.inquiry.entity.Inquiry, com.ssafy.bangrang.domain.inquiry.entity.QInquiry>createList("inquiries", com.ssafy.bangrang.domain.inquiry.entity.Inquiry.class, com.ssafy.bangrang.domain.inquiry.entity.QInquiry.class, PathInits.DIRECT2);

    public final ListPath<com.ssafy.bangrang.domain.event.entity.Likes, com.ssafy.bangrang.domain.event.entity.QLikes> likes = this.<com.ssafy.bangrang.domain.event.entity.Likes, com.ssafy.bangrang.domain.event.entity.QLikes>createList("likes", com.ssafy.bangrang.domain.event.entity.Likes.class, com.ssafy.bangrang.domain.event.entity.QLikes.class, PathInits.DIRECT2);

    public final ListPath<com.ssafy.bangrang.domain.map.entity.MemberMapArea, com.ssafy.bangrang.domain.map.entity.QMemberMapArea> memberMapAreas = this.<com.ssafy.bangrang.domain.map.entity.MemberMapArea, com.ssafy.bangrang.domain.map.entity.QMemberMapArea>createList("memberMapAreas", com.ssafy.bangrang.domain.map.entity.MemberMapArea.class, com.ssafy.bangrang.domain.map.entity.QMemberMapArea.class, PathInits.DIRECT2);

    public final ListPath<com.ssafy.bangrang.domain.map.entity.MemberMarker, com.ssafy.bangrang.domain.map.entity.QMemberMarker> memberMarkers = this.<com.ssafy.bangrang.domain.map.entity.MemberMarker, com.ssafy.bangrang.domain.map.entity.QMemberMarker>createList("memberMarkers", com.ssafy.bangrang.domain.map.entity.MemberMarker.class, com.ssafy.bangrang.domain.map.entity.QMemberMarker.class, PathInits.DIRECT2);

    public final StringPath nickname = createString("nickname");

    public final EnumPath<com.ssafy.bangrang.domain.member.model.vo.AlarmReceivedStatus> notificationAlarmStatus = createEnum("notificationAlarmStatus", com.ssafy.bangrang.domain.member.model.vo.AlarmReceivedStatus.class);

    public final EnumPath<com.ssafy.bangrang.domain.member.model.vo.AlarmReceivedStatus> rankingAlarmStatus = createEnum("rankingAlarmStatus", com.ssafy.bangrang.domain.member.model.vo.AlarmReceivedStatus.class);

    //inherited
    public final StringPath refreshToken = _super.refreshToken;

    public final EnumPath<com.ssafy.bangrang.domain.member.model.vo.SocialProvider> socialProvider = createEnum("socialProvider", com.ssafy.bangrang.domain.member.model.vo.SocialProvider.class);

    public final ListPath<Stamp, QStamp> stamps = this.<Stamp, QStamp>createList("stamps", Stamp.class, QStamp.class, PathInits.DIRECT2);

    public QAppMember(String variable) {
        super(AppMember.class, forVariable(variable));
    }

    public QAppMember(Path<? extends AppMember> path) {
        super(path.getType(), path.getMetadata());
    }

    public QAppMember(PathMetadata metadata) {
        super(AppMember.class, metadata);
    }

}

