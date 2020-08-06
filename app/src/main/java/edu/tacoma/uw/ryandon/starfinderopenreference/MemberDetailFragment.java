//package edu.tacoma.uw.ryandon.starfinderopenreference;
//
//import android.app.Activity;
//import android.os.Bundle;
//import android.view.LayoutInflater;
//import android.view.View;
//import android.view.ViewGroup;
//import android.widget.TextView;
//
//import androidx.fragment.app.Fragment;
//
//import com.google.android.material.appbar.CollapsingToolbarLayout;
//
//import edu.tacoma.uw.ryandon.starfinderopenreference.model.Members;
//import edu.tacoma.uw.ryandon.starfinderopenreference.model.MembersContent;
//
//public class MemberDetailFragment extends Fragment {
//    private Members mMember;
//    /**
//     * The fragment argument representing the item ID that this fragment
//     * represents.
//     */
//    public static final String ARG_ITEM_ID = "item_id";
//
//    /**
//     * The dummy content this fragment is presenting.
//     */
//    private MembersContent.MemberItem mItem;
//
//    /**
//     * Mandatory empty constructor for the fragment manager to instantiate the
//     * fragment (e.g. upon screen orientation changes).
//     */
//
//    public MemberDetailFragment() {
//    }
//
//    @Override
//    public void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//
//        if (getArguments().containsKey(ARG_ITEM_ID)) {
//            // Load the dummy content specified by the fragment
//            // arguments. In a real-world scenario, use a Loader
//            // to load content from a content provider.
//            mMember = (Members) getArguments().getSerializable(ARG_ITEM_ID);
//
//            Activity activity = this.getActivity();
//            CollapsingToolbarLayout appBarLayout = (CollapsingToolbarLayout) activity.findViewById(R.id.toolbar_layout);
//            if (appBarLayout != null) {
//                appBarLayout.setTitle(mMember.getmMemberID());
//            }
//        }
//    }
//
//    @Override
//    public View onCreateView(LayoutInflater inflater, ViewGroup container,
//                             Bundle savedInstanceState) {
//        View rootView = inflater.inflate(R.layout.member_detail, container, false);
//
//        // Show the dummy content as text in a TextView.
//        if (mMember != null) {
//            ((TextView) rootView.findViewById(R.id.item_detail_id)).setText(mMember.getmMemberID());
//            ((TextView) rootView.findViewById(R.id.item_detail_first)).setText(mMember.getmFirstName());
//            ((TextView) rootView.findViewById(R.id.item_detail_last)).setText(mMember.getmLastName());
//            ((TextView) rootView.findViewById(R.id.item_detail_user)).setText(mMember.getmUsername());
//
//        }
//
//        return rootView;
//    }
//}
