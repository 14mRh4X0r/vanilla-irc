/*
 * Copyright (C) 2010-2015 Mojang AB
 *
 * Parts of this program are marked with "VanillaIRC". These will be referred
 * to as "Modification" from this point on.
 *
 * Modification Copyright (C) 2014-2015 MinecraftOnline
 *
 * The Modification is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Affero General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * The Modification is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU Affero General Public License for more details.
 *
 * You should have received a copy of the GNU Affero General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.google.common.collect.Sets;
import com.minecraftonline.vanillairc.ChatHandler; // VanillaIRC
import com.minecraftonline.vanillairc.ObfuscationHelper; // VanillaIRC
import com.minecraftonline.vanillairc.VanillaIRC; // VanillaIRC
import com.mojang.authlib.GameProfile;
import io.netty.buffer.Unpooled;
import java.io.File;
import java.net.SocketAddress;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import javax.annotation.Nullable;
import net.minecraft.server.MinecraftServer;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;


public abstract class mn {
    public static final File a = new File("banned-players.json");
    public static final File b = new File("banned-ips.json");
    public static final File c = new File("ops.json");
    public static final File d = new File("whitelist.json");
    private static final Logger f = LogManager.getLogger();
    private static final SimpleDateFormat g = new SimpleDateFormat("yyyy-MM-dd \'at\' HH:mm:ss z");
    private final MinecraftServer h;
    private final List<ls> i = Lists.newArrayList();
    private final Map<UUID, ls> j = Maps.newHashMap();
    private final ms k;
    private final mk l;
    private final mo m;
    private final mu n;
    private final Map<UUID, np> o;
    private azn p;
    private boolean q;
    protected int e;
    private int r;
    private ahw.a s;
    private boolean t;
    private int u;

    { ObfuscationHelper.setLoginManager(this, mn.class); } // VanillaIRC -- store static reference to object

    public mn(MinecraftServer var1) {
        this.k = new ms(a);
        this.l = new mk(b);
        this.m = new mo(c);
        this.n = new mu(d);
        this.o = Maps.newHashMap();
        this.h = var1;
        this.k.a(false);
        this.l.a(false);
        this.e = 8;
    }

    public void a(el var1, ls var2) {
        GameProfile var3 = var2.cL();
        mj var4 = this.h.aA();
        GameProfile var5 = var4.a(var3.getId());
        String var6 = var5 == null ? var3.getName() : var5.getName();

        var4.a(var3);
        dp var7 = this.a(var2);

        var2.a((aht) this.h.a(var2.am));
        var2.c.a((lq) var2.l);
        String var8 = "local";

        if (var1.b() != null) {
            var8 = var1.b().toString();
        }

        f.info(var2.h_() + "[" + var8 + "] logged in with entity id " + var2.O() + " at (" + var2.p + ", " + var2.q + ", " + var2.r + ")");
        lq var9 = this.h.a(var2.am);
        azg var10 = var9.T();
        ck var11 = var9.R();

        this.a(var2, (ls) null, var9);
        mc var12 = new mc(this.h, var1, var2);

        var12.a((fg) (new gt(var2.O(), var2.c.b(), var10.s(), var9.s.p().a(), var9.ae(), this.p(), var10.t(), var9.U().b("reducedDebugInfo"))));
        var12.a((fg) (new gi("MC|Brand", (new en(Unpooled.buffer())).a(this.c().getServerModName()))));
        var12.a((fg) (new fx(var10.x(), var10.y())));
        var12.a((fg) (new hw(var11)));
        var12.a((fg) (new gy(var2.bK)));
        var12.a((fg) (new hk(var2.bs.d)));
        this.f(var2);
        var2.E().d();
        var2.E().b(var2);
        this.a((kx) var9.ad(), var2);
        this.h.aC();
        fc var13;

        if (!var2.h_().equalsIgnoreCase(var6)) {
            var13 = new fc("multiplayer.player.joined.renamed", new Object[] { var2.i_(), var6});
        } else {
            var13 = new fc("multiplayer.player.joined", new Object[] { var2.i_()});
        }

        // VanillaIRC start - field "a" blocks class "a"
        try {
            var13.b().a((a) Class.forName("a").getDeclaredField("o").get(null));
        } catch (ReflectiveOperationException e) {
            throw new RuntimeException(e);
        } // VanillaIRC end
        this.a((ev) var13);
        this.c(var2);
        var12.a(var2.p, var2.q, var2.r, var2.v, var2.w);
        this.b(var2, var9);
        if (!this.h.X().isEmpty()) {
            var2.a(this.h.X(), this.h.Y());
        }

        Iterator var14 = var2.bP().iterator();

        while (var14.hasNext()) {
            rl var15 = (rl) var14.next();

            var12.a((fg) (new ig(var2.O(), var15)));
        }

        if (var7 != null) {
            if (var7.b("RootVehicle", 10)) {
                dp var19 = var7.o("RootVehicle");
                rr var21 = ass.a(var19.o("Entity"), var9, true);

                if (var21 != null) {
                    UUID var16 = var19.a("Attach");
                    Iterator var17;
                    rr var18;

                    if (var21.bc().equals(var16)) {
                        var2.a(var21, true);
                    } else {
                        var17 = var21.bw().iterator();

                        while (var17.hasNext()) {
                            var18 = (rr) var17.next();
                            if (var18.bc().equals(var16)) {
                                var2.a(var18, true);
                                break;
                            }
                        }
                    }

                    if (!var2.aI()) {
                        f.warn("Couldn\'t reattach entity to player");
                        var9.f(var21);
                        var17 = var21.bw().iterator();

                        while (var17.hasNext()) {
                            var18 = (rr) var17.next();
                            var9.f(var18);
                        }
                    }
                }
            } else if (var7.b("Riding", 10)) {
                rr var20 = ass.a(var7.o("Riding"), var9, true);

                if (var20 != null) {
                    var2.a(var20, true);
                }
            }
        }

        var2.j_();
    }

    protected void a(kx var1, ls var2) {
        HashSet var3 = Sets.newHashSet();
        Iterator var4 = var1.g().iterator();

        while (var4.hasNext()) {
            bbj var5 = (bbj) var4.next();

            var2.a.a((fg) (new hu(var5, 0)));
        }

        for (int var9 = 0; var9 < 19; ++var9) {
            bbi var10 = var1.a(var9);

            if (var10 != null && !var3.contains(var10)) {
                List var6 = var1.d(var10);
                Iterator var7 = var6.iterator();

                while (var7.hasNext()) {
                    fg var8 = (fg) var7.next();

                    var2.a.a(var8);
                }

                var3.add(var10);
            }
        }

    }

    public void a(lq[] var1) {
        this.p = var1[0].S().e();
        var1[0].aj().a(new art() {
            public void a(arv var1, double var2) {
                mn.this.a((fg) (new hi(var1, hi.a.a)));
            }

            public void a(arv var1, double var2, double var4, long var6) {
                mn.this.a((fg) (new hi(var1, hi.a.b)));
            }

            public void a(arv var1, double var2, double var4) {
                mn.this.a((fg) (new hi(var1, hi.a.c)));
            }

            public void a(arv var1, int var2) {
                mn.this.a((fg) (new hi(var1, hi.a.e)));
            }

            public void b(arv var1, int var2) {
                mn.this.a((fg) (new hi(var1, hi.a.f)));
            }

            public void b(arv var1, double var2) {}

            public void c(arv var1, double var2) {}
        });
    }

    public void a(ls var1, lq var2) {
        lq var3 = var1.x();

        if (var2 != null) {
            var2.w().b(var1);
        }

        var3.w().a(var1);
        var3.r().c((int) var1.p >> 4, (int) var1.r >> 4);
    }

    public int d() {
        return lw.b(this.s());
    }

    public dp a(ls var1) {
        dp var2 = this.h.d[0].T().h();
        dp var3;

        if (var1.h_().equals(this.h.Q()) && var2 != null) {
            var3 = this.h.aI().a((oy) oz.b, (dp) var2);
            var1.f(var3);
            f.debug("loading single player");
        } else {
            var3 = this.p.b(var1);
        }

        return var3;
    }

    protected void b(ls var1) {
        this.p.a(var1);
        np var2 = (np) this.o.get(var1.bc());

        if (var2 != null) {
            var2.b();
        }

    }

    public void c(ls var1) {
        this.i.add(var1);
        this.j.put(var1.bc(), var1);
        this.a((fg) (new ha(ha.a.a, new ls[] { var1})));
        lq var2 = this.h.a(var1.am);

        for (int var3 = 0; var3 < this.i.size(); ++var3) {
            var1.a.a((fg) (new ha(ha.a.a, new ls[] { (ls) this.i.get(var3)})));
        }

        var2.a((rr) var1);
        this.a((ls) var1, (lq) null);
    }

    public void d(ls var1) {
        var1.x().w().c(var1);
    }

    public void e(ls var1) {
        lq var2 = var1.x();

        var1.b((nq) nu.f);
        this.b(var1);
        if (var1.aI()) {
            rr var3 = var1.bx();

            if (var3.b(ls.class).size() == 1) {
                f.debug("Removing player mount");
                var1.p();
                var2.f(var3);
                Iterator var4 = var3.bw().iterator();

                while (var4.hasNext()) {
                    rr var5 = (rr) var4.next();

                    var2.f(var5);
                }

                var2.a(var1.ab, var1.ad).e();
            }
        }

        var2.e(var1);
        var2.w().b(var1);
        this.i.remove(var1);
        UUID var6 = var1.bc();
        ls var7 = (ls) this.j.get(var6);

        if (var7 == var1) {
            this.j.remove(var6);
            this.o.remove(var6);
        }

        this.a((fg) (new ha(ha.a.e, new ls[] { var1})));
    }

    public String a(SocketAddress var1, GameProfile var2) {
        String var4;

        if (this.k.a(var2)) {
            mt var5 = (mt) ((mr) this.k).b(var2); // VanillaIRC -- obfuscation anomaly: cast this.k to mr

            var4 = "You are banned from this server!\nReason: " + var5.d();
            if (var5.c() != null) {
                var4 = var4 + "\nYour ban will be removed on " + g.format(var5.c());
            }

            return var4;
        } else if (!this.e(var2)) {
            return "You are not white-listed on this server!";
        } else if (this.l.a(var1)) {
            ml var3 = this.l.b(var1);

            var4 = "Your IP address is banned from this server!\nReason: " + var3.d();
            if (var3.c() != null) {
                var4 = var4 + "\nYour ban will be removed on " + g.format(var3.c());
            }

            return var4;
        } else {
            return this.i.size() >= this.e && !this.f(var2) ? "The server is full!" : null;
        }
    }

    public ls g(GameProfile var1) {
        UUID var2 = zj.a(var1);
        ArrayList var3 = Lists.newArrayList();

        for (int var4 = 0; var4 < this.i.size(); ++var4) {
            ls var5 = (ls) this.i.get(var4);

            if (var5.bc().equals(var2)) {
                var3.add(var5);
            }
        }

        ls var7 = (ls) this.j.get(var1.getId());

        if (var7 != null && !var3.contains(var7)) {
            var3.add(var7);
        }

        Iterator var8 = var3.iterator();

        while (var8.hasNext()) {
            ls var6 = (ls) var8.next();

            var6.a.c("You logged in from another location");
        }

        Object var9;

        if (this.h.V()) {
            var9 = new ll(this.h.a(0));
        } else {
            var9 = new lt(this.h.a(0));
        }

        return new ls(this.h, this.h.a(0), var1, (lt) var9);
    }

    public ls a(ls var1, int var2, boolean var3) {
        var1.x().v().b(var1);
        var1.x().v().b((rr) var1);
        var1.x().w().b(var1);
        this.i.remove(var1);
        this.h.a(var1.am).f(var1);
        ck var4 = var1.cP();
        boolean var5 = var1.cQ();

        var1.am = var2;
        Object var6;

        if (this.h.V()) {
            var6 = new ll(this.h.a(var1.am));
        } else {
            var6 = new lt(this.h.a(var1.am));
        }

        ls var7 = new ls(this.h, this.h.a(var1.am), var1.cL(), (lt) var6);

        var7.a = var1.a;
        var7.a((zj) var1, var3);
        var7.f(var1.O());
        var7.v(var1);
        var7.a((rz) var1.cs());
        Iterator var8 = var1.P().iterator();

        while (var8.hasNext()) {
            String var9 = (String) var8.next();

            var7.a((String) var9);
        }

        lq var10 = this.h.a(var1.am);

        this.a(var7, var1, var10);
        ck var11;

        if (var4 != null) {
            var11 = zj.a(this.h.a(var1.am), var4, var5);
            if (var11 != null) {
                var7.b((double) ((float) var11.p() + 0.5F), (double) ((float) var11.q() + 0.1F), (double) ((float) var11.r() + 0.5F), 0.0F, 0.0F);
                var7.a((ck) var4, var5);
            } else {
                var7.a.a((fg) (new go(0, 0.0F)));
            }
        }

        var10.r().c((int) var7.p >> 4, (int) var7.r >> 4);

        while (!var10.a((rr) var7, (bbe) var7.bm()).isEmpty() && var7.q < 256.0D) {
            var7.b(var7.p, var7.q + 1.0D, var7.r);
        }

        var7.a.a((fg) (new hg(var7.am, var7.l.ae(), var7.l.T().t(), var7.c.b())));
        var11 = var10.R();
        var7.a.a(var7.p, var7.q, var7.r, var7.v, var7.w);
        var7.a.a((fg) (new hw(var11)));
        var7.a.a((fg) (new hq(var7.bN, var7.bM, var7.bL)));
        this.b(var7, var10);
        this.f(var7);
        var10.w().a(var7);
        var10.a((rr) var7);
        this.i.add(var7);
        this.j.put(var7.bc(), var7);
        var7.j_();
        var7.c(var7.bR());
        return var7;
    }

    public void f(ls var1) {
        GameProfile var2 = var1.cL();
        int var3 = this.h(var2) ? this.m.a(var2) : 0;

        var3 = this.h.R() && this.h.d[0].T().u() ? 4 : var3;
        var3 = this.t ? 4 : var3;
        this.b(var1, var3);
    }

    public void a(ls var1, int var2) {
        int var3 = var1.am;
        lq var4 = this.h.a(var1.am);

        var1.am = var2;
        lq var5 = this.h.a(var1.am);

        var1.a.a((fg) (new hg(var1.am, var1.l.ae(), var1.l.T().t(), var1.c.b())));
        this.f(var1);
        var4.f(var1);
        var1.F = false;
        this.a(var1, var3, var4, var5);
        this.a(var1, var4);
        var1.a.a(var1.p, var1.q, var1.r, var1.v, var1.w);
        var1.c.a(var5);
        var1.a.a((fg) (new gy(var1.bK)));
        this.b(var1, var5);
        this.g(var1);
        Iterator var6 = var1.bP().iterator();

        while (var6.hasNext()) {
            rl var7 = (rl) var6.next();

            var1.a.a((fg) (new ig(var1.O(), var7)));
        }

    }

    public void a(rr var1, int var2, lq var3, lq var4) {
        double var5 = var1.p;
        double var7 = var1.r;
        double var9 = 8.0D;
        float var11 = var1.v;

        var3.C.a("moving");
        if (var1.am == -1) {
            var5 = on.a(var5 / var9, var4.aj().b() + 16.0D, var4.aj().d() - 16.0D);
            var7 = on.a(var7 / var9, var4.aj().c() + 16.0D, var4.aj().e() - 16.0D);
            var1.b(var5, var1.q, var7, var1.v, var1.w);
            if (var1.au()) {
                var3.a(var1, false);
            }
        } else if (var1.am == 0) {
            var5 = on.a(var5 * var9, var4.aj().b() + 16.0D, var4.aj().d() - 16.0D);
            var7 = on.a(var7 * var9, var4.aj().c() + 16.0D, var4.aj().e() - 16.0D);
            var1.b(var5, var1.q, var7, var1.v, var1.w);
            if (var1.au()) {
                var3.a(var1, false);
            }
        } else {
            ck var12;

            if (var2 == 1) {
                var12 = var4.R();
            } else {
                var12 = var4.p();
            }

            var5 = (double) var12.p();
            var1.q = (double) var12.q();
            var7 = (double) var12.r();
            var1.b(var5, var1.q, var7, 90.0F, 0.0F);
            if (var1.au()) {
                var3.a(var1, false);
            }
        }

        var3.C.b();
        if (var2 != 1) {
            var3.C.a("placing");
            var5 = (double) on.a((int) var5, -29999872, 29999872);
            var7 = (double) on.a((int) var7, -29999872, 29999872);
            if (var1.au()) {
                var1.b(var5, var1.q, var7, var1.v, var1.w);
                var4.x().a(var1, var11);
                var4.a(var1);
                var4.a(var1, false);
            }

            var3.C.b();
        }

        var1.a((aht) var4);
    }

    public void e() {
        if (++this.u > 600) {
            this.a((fg) (new ha(ha.a.c, this.i)));
            this.u = 0;
        }

    }

    public void a(fg<?> var1) {
        for (int var2 = 0; var2 < this.i.size(); ++var2) {
            ((ls) this.i.get(var2)).a.a(var1);
        }

    }

    public void a(fg<?> var1, int var2) {
        for (int var3 = 0; var3 < this.i.size(); ++var3) {
            ls var4 = (ls) this.i.get(var3);

            if (var4.am == var2) {
                var4.a.a(var1);
            }
        }

    }

    public void a(zj var1, ev var2) {
        bbo var3 = var1.aO();

        if (var3 != null) {
            Collection var4 = var3.d();
            Iterator var5 = var4.iterator();

            while (var5.hasNext()) {
                String var6 = (String) var5.next();
                ls var7 = this.a(var6);

                if (var7 != null && var7 != var1) {
                    var7.a(var2);
                }
            }

        }
    }

    public void b(zj var1, ev var2) {
        bbo var3 = var1.aO();

        if (var3 == null) {
            this.a(var2);
        } else {
            for (int var4 = 0; var4 < this.i.size(); ++var4) {
                ls var5 = (ls) this.i.get(var4);

                if (var5.aO() != var3) {
                    var5.a(var2);
                }
            }

        }
    }

    public String b(boolean var1) {
        String var2 = "";
        ArrayList var3 = Lists.newArrayList(this.i);

        for (int var4 = 0; var4 < var3.size(); ++var4) {
            if (var4 > 0) {
                var2 = var2 + ", ";
            }

            var2 = var2 + ((ls) var3.get(var4)).h_();
            if (var1) {
                var2 = var2 + " (" + ((ls) var3.get(var4)).bd() + ")";
            }
        }

        return var2;
    }

    public String[] f() {
        String[] var1 = new String[this.i.size()];

        for (int var2 = 0; var2 < this.i.size(); ++var2) {
            var1[var2] = ((ls) this.i.get(var2)).h_();
        }

        return var1;
    }

    public GameProfile[] g() {
        GameProfile[] var1 = new GameProfile[this.i.size()];

        for (int var2 = 0; var2 < this.i.size(); ++var2) {
            var1[var2] = ((ls) this.i.get(var2)).cL();
        }

        return var1;
    }

    public ms h() {
        return this.k;
    }

    public mk i() {
        return this.l;
    }

    public void a(GameProfile var1) {
        int var2 = this.h.q();

        ((mr) this.m).a((mq) (new mp(var1, this.h.q(), this.m.b(var1)))); // VanillaIRC -- obfuscation anomaly: cast this.m to mr
        this.b(this.a(var1.getId()), var2);
    }

    public void b(GameProfile var1) {
        this.m.c(var1);
        this.b(this.a(var1.getId()), 0);
    }

    private void b(ls var1, int var2) {
        if (var1 != null && var1.a != null) {
            byte var3;

            if (var2 <= 0) {
                var3 = 24;
            } else if (var2 >= 4) {
                var3 = 28;
            } else {
                var3 = (byte) (24 + var2);
            }

            var1.a.a((fg) (new gl(var1, var3)));
        }

    }

    public boolean e(GameProfile var1) {
        return !this.q || this.m.d(var1) || this.n.d(var1);
    }

    public boolean h(GameProfile var1) {
        return this.m.d(var1) || this.h.R() && this.h.d[0].T().u() && this.h.Q().equalsIgnoreCase(var1.getName()) || this.t;
    }

    @Nullable
    public ls a(String var1) {
        Iterator var2 = this.i.iterator();

        ls var3;

        do {
            if (!var2.hasNext()) {
                return null;
            }

            var3 = (ls) var2.next();
        } while (!var3.h_().equalsIgnoreCase(var1));

        return var3;
    }

    public void a(@Nullable zj var1, double var2, double var4, double var6, double var8, int var10, fg<?> var11) {
        for (int var12 = 0; var12 < this.i.size(); ++var12) {
            ls var13 = (ls) this.i.get(var12);

            if (var13 != var1 && var13.am == var10) {
                double var14 = var2 - var13.p;
                double var16 = var4 - var13.q;
                double var18 = var6 - var13.r;

                if (var14 * var14 + var16 * var16 + var18 * var18 < var8 * var8) {
                    var13.a.a(var11);
                }
            }
        }

    }

    public void j() {
        for (int var1 = 0; var1 < this.i.size(); ++var1) {
            this.b((ls) this.i.get(var1));
        }

    }

    public void d(GameProfile var1) {
        ((mr) this.n).a((mq) (new mv(var1))); // VanillaIRC -- obfuscation anomaly: cast this.m to mr
    }

    public void c(GameProfile var1) {
        this.n.c(var1);
    }

    public mu k() {
        return this.n;
    }

    public String[] l() {
        return this.n.a();
    }

    public mo m() {
        return this.m;
    }

    public String[] n() {
        return this.m.a();
    }

    public void a() {}

    public void b(ls var1, lq var2) {
        arv var3 = this.h.d[0].aj();

        var1.a.a((fg) (new hi(var3, hi.a.d)));
        var1.a.a((fg) (new hx(var2.P(), var2.Q(), var2.U().b("doDaylightCycle"))));
        if (var2.W()) {
            var1.a.a((fg) (new go(1, 0.0F)));
            var1.a.a((fg) (new go(7, var2.j(1.0F))));
            var1.a.a((fg) (new go(8, var2.h(1.0F))));
        }

    }

    public void g(ls var1) {
        var1.a(var1.bt);
        var1.u();
        var1.a.a((fg) (new hk(var1.bs.d)));
    }

    public int o() {
        return this.i.size();
    }

    public int p() {
        return this.e;
    }

    public String[] q() {
        return this.h.d[0].S().e().f();
    }

    public boolean r() {
        return this.q;
    }

    public void a(boolean var1) {
        this.q = var1;
    }

    public List<ls> b(String var1) {
        ArrayList var2 = Lists.newArrayList();
        Iterator var3 = this.i.iterator();

        while (var3.hasNext()) {
            ls var4 = (ls) var3.next();

            if (var4.A().equals(var1)) {
                var2.add(var4);
            }
        }

        return var2;
    }

    public int s() {
        return this.r;
    }

    public MinecraftServer c() {
        return this.h;
    }

    public dp t() {
        return null;
    }

    private void a(ls var1, ls var2, aht var3) {
        if (var2 != null) {
            var1.c.a(var2.c.b());
        } else if (this.s != null) {
            var1.c.a(this.s);
        }

        var1.c.b(var3.T().q());
    }

    public void u() {
        for (int var1 = 0; var1 < this.i.size(); ++var1) {
            ((ls) this.i.get(var1)).a.c("Server closed");
        }

    }

    // VanillaIRC start -- redirect for chat injection
    public void a(ev var1, boolean var2) {
        this.sendMessageToPlayers(var1, var2);
        
        String translated = var1.c();
        ChatHandler ch = VanillaIRC.getHandler();
        if (ch != null) {
            ch.handleGameMessage(translated);
        }
    }
    
    public void sendMessageToPlayers(ev var1, boolean var2) {
        this.h.a(var1);
        int var3 = var2 ? 1 : 0;

        this.a((fg) (new fz(var1, (byte) var3)));
    }

    public void sendFromJson(String json) {
        this.sendMessageToPlayers(ev.a.a(json), false);
    } // VanillaIRC end

    public void a(ev var1) {
        this.a(var1, true);
    }

    public np a(zj var1) {
        UUID var2 = var1.bc();
        np var3 = var2 == null ? null : (np) this.o.get(var2);

        if (var3 == null) {
            File var4 = new File(this.h.a(0).S().b(), "stats");
            File var5 = new File(var4, var2.toString() + ".json");

            if (!var5.exists()) {
                File var6 = new File(var4, var1.h_() + ".json");

                if (var6.exists() && var6.isFile()) {
                    var6.renameTo(var5);
                }
            }

            var3 = new np(this.h, var5);
            var3.a();
            this.o.put(var2, var3);
        }

        return var3;
    }

    public void a(int var1) {
        this.r = var1;
        if (this.h.d != null) {
            lq[] var2 = this.h.d;
            int var3 = var2.length;

            for (int var4 = 0; var4 < var3; ++var4) {
                lq var5 = var2[var4];

                if (var5 != null) {
                    var5.w().a(var1);
                    var5.v().a(var1);
                }
            }

        }
    }

    public List<ls> v() {
        return this.i;
    }

    public ls a(UUID var1) {
        return (ls) this.j.get(var1);
    }

    public boolean f(GameProfile var1) {
        return false;
    }
}
