// ========================================
// Locket Gold Premium - Enhanced Version
// ⚡ Performance: Ultra-Fast & Reliable
// 🔐 Lifetime Premium Unlock
// 📅 Version: 2.0 (2025-11-02)
// ========================================

(function() {
  'use strict';
  
  // ========= Constants (Optimized & Consistent) ========= //
  const PURCHASE_DATE = "2025-11-01T00:00:00Z";      // Gần với ngày hiện tại
  const EXPIRES_DATE = "2099-12-31T23:59:59Z";       // Nhất quán, date dài nhất
  const PRODUCT_ID = "com.locket.premium.yearly";    // Chuẩn iOS bundle ID
  const APP_VERSION = "9999";                         // High version number
  
  // ========= Mapping Configuration ========= //
  const APP_MAPPING = {
    '%E8%BD%A6%E7%A5%A8%E7%A5%A8': ['vip+watch_vip'],
    'Locket': ['Gold']
  };
  
  // ========= Get User-Agent (Optimized) ========= //
  const headers = $request.headers;
  const ua = headers["User-Agent"] || headers["user-agent"] || "";
  
  // ========= Parse Response Body (Fast Parse) ========= //
  let responseObj;
  try {
    responseObj = JSON.parse($response.body);
    
    // Ensure structure exists (Fast initialization)
    if (!responseObj.subscriber) {
      responseObj.subscriber = {};
    }
    if (!responseObj.subscriber.subscriptions) {
      responseObj.subscriber.subscriptions = {};
    }
    if (!responseObj.subscriber.entitlements) {
      responseObj.subscriber.entitlements = {};
    }
  } catch (error) {
    // Fast error recovery
    responseObj = {
      subscriber: {
        subscriptions: {},
        entitlements: {}
      }
    };
  }
  
  // ========= Premium Data (Pre-built for speed) ========= //
  const subscriptionData = {
    is_sandbox: false,
    ownership_type: "PURCHASED",
    billing_issues_detected_at: null,
    period_type: "normal",
    expires_date: EXPIRES_DATE,                    // ✅ Consistent date
    grace_period_expires_date: null,
    unsubscribe_detected_at: null,
    original_purchase_date: PURCHASE_DATE,
    purchase_date: PURCHASE_DATE,
    store: "app_store"
  };
  
  const entitlementData = {
    grace_period_expires_date: null,
    purchase_date: PURCHASE_DATE,
    product_identifier: PRODUCT_ID,                // ✅ Correct product ID
    expires_date: EXPIRES_DATE                     // ✅ Same as subscription
  };
  
  // ========= Additional metadata (Optional but recommended) ========= //
  responseObj.subscriber.original_app_user_id = responseObj.subscriber.original_app_user_id || $persistentStore.read("locket_user_id") || "PREMIUM_USER_" + Date.now();
  responseObj.subscriber.original_application_version = APP_VERSION;
  responseObj.subscriber.first_seen = PURCHASE_DATE;
  responseObj.subscriber.last_seen = new Date().toISOString();
  responseObj.subscriber.management_url = null;
  responseObj.subscriber.non_subscriptions = {};
  responseObj.subscriber.other_purchases = {};
  
  // ========= Apply Mapping (Optimized Logic) ========= //
  let entitlementKey = "Gold";  // Default
  
  // Fast mapping lookup
  for (const key in APP_MAPPING) {
    if (ua.indexOf(key) !== -1) {
      entitlementKey = APP_MAPPING[key][0];
      break;
    }
  }
  
  // Apply premium data
  responseObj.subscriber.subscriptions[PRODUCT_ID] = subscriptionData;
  responseObj.subscriber.entitlements[entitlementKey] = entitlementData;
  
  // ========= Return Response (Fast stringify) ========= //
  $done({ body: JSON.stringify(responseObj) });
  
})();
